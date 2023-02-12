package shop.mtcoding.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog.dto.ResponseDto;
import shop.mtcoding.blog.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blog.dto.board.BoardResp.BoardDetailResqDto;
import shop.mtcoding.blog.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.blog.ex.CustomApiException;
import shop.mtcoding.blog.ex.CustomException;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.BoardRepository;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private HttpSession session;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    private void mockSession(){
        User principal = new User();
        principal.setId(1);
        principal.setUsername("ssar");
        principal.setPassword("1234");
        session.setAttribute("principal", principal);
    };
    
    @GetMapping("/")
    public String main(Model model){
        mockSession();
        List<BoardMainRespDto> boardList = boardRepository.findAllWithUser();
        model.addAttribute("boardList", boardList);
        return "board/main";
    }
    @GetMapping("/board/write")
    public String WriteBoard(){ 

        return "board/writeForm";
    }
    @GetMapping("/board/detail/{id}")
    public String detail(@PathVariable int id, Model model){
        BoardDetailResqDto dto = boardRepository.findByIdWithUser(id);
        model.addAttribute("dto", dto);
        return "board/detail";
    } 
    @GetMapping("/board/updateForm/{id}")
    public String updateForm(@PathVariable int id){
        User principal = (User) session.getAttribute("principal");
        if( principal == null ){
            throw new CustomException("로그인이 필요한 페이지 입니다.", HttpStatus.UNAUTHORIZED);
        }
        Board board = boardRepository.findById(id);
        if ( board == null ){
            throw new CustomException("존재하지 않는 글을 수정할 수 없습니다.", HttpStatus.FORBIDDEN);
        }
        if ( board.getUserId() != principal.getId()){
            throw new CustomException("글을 수정할 권한이 없습니다.", HttpStatus.UNAUTHORIZED);
        }


        return "board/updateForm";
    }
    @PostMapping("/board/write")
    public String save(BoardSaveReqDto bDto){
        User principal = (User) session.getAttribute("principal");
        if( principal == null ){
            throw new CustomException("로그인이 필요한 페이지 입니다.", HttpStatus.UNAUTHORIZED);
        }
        if( bDto.getTitle()==null||bDto.getTitle().isEmpty()){
            throw new CustomException("제목을 입력하세요");   
        }
        if( bDto.getTitle().length()>100){
            throw new CustomException("제목은 100자를 초과할 수 없습니다.");   
        }
        if ( bDto.getContent()==null||bDto.getContent().isEmpty()){
            throw new CustomException("내용을 입력하세요");
        }
        boardService.글쓰기(bDto, principal.getId());
        return "redirect:/";
    }
    @DeleteMapping("/board/delete/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable int id){
        User principal = (User) session.getAttribute("principal");
        if( principal == null ){
            throw new CustomApiException("로그인이 필요한 페이지 입니다.", HttpStatus.UNAUTHORIZED);
        }
        boardService.글삭제(id, principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "삭제 성공", null), HttpStatus.OK);
        
    }
}
