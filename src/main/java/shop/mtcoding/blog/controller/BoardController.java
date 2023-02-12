package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    
    @GetMapping("/")
    public String main(){

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
}
