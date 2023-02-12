package shop.mtcoding.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blog.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.blog.ex.CustomException;
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
        model.addAttribute("boardLsit", boardList);
        return "board/main";
    }
    @GetMapping("/board/write")
    public String WriteBoard(){ 

        return "board/writeForm";
    }
    @GetMapping("/board/detail/1")
    public String detail(){

        return "board/detail";
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
}
