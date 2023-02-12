package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog.dto.board.BoardReq.BoardSaveReqDto;
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

    private void mockSession(){
        User principal = new User();
        principal.setUsername("ssar");
        principal.setPassword("1234");
        session.setAttribute("principal", principal);
    };
    
    @GetMapping("/")
    public String main(){
        mockSession();
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
    public String writeBoard(BoardSaveReqDto bDto){
        if( bDto.getTitle()==null||bDto.getTitle().isEmpty()){
            throw new CustomException("제목을 입력하세요");   
        }
        if( bDto.getTitle().length()>100){
            throw new CustomException("제목은 100자를 초과할 수 없습니다.");   
        }
        if ( bDto.getContent()==null||bDto.getContent().isEmpty()){
            throw new CustomException("내용을 입력하세요");
        }
        int id = boardService.글쓰기(bDto);
        return "redirect:/board/detail/"+id;
    }
}
