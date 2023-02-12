package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.blog.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.blog.ex.CustomException;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.UserService;

@Controller
public class UserController {

    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String loginForm(){

        return "user/loginForm";
    }
    @GetMapping("/join")
    public String joinForm(){

        return "user/joinForm";
    }
    @GetMapping("/update")
    public String updateForm(){

        return "user/updateForm";
    }
    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/";
    }
    @PostMapping("/join")
    public String join(UserJoinReqDto uDto){
        if( uDto.getUsername()==null||uDto.getUsername().isEmpty()){
            throw new CustomException("유저네임을 입력하세요");   
        }
        if ( uDto.getPassword()==null||uDto.getPassword().isEmpty()){
            throw new CustomException("패스워드를 입력하세요");
        }
        if( uDto.getEmail()==null||uDto.getEmail().isEmpty()){
            throw new CustomException("이메일을 입력하세요");
        }
        userService.회원가입(uDto);
        return "redirect:/login";
    }
    @PostMapping("/login")
    public String login(UserLoginReqDto uDto){
        if( uDto.getUsername()==null||uDto.getUsername().isEmpty()){
            throw new CustomException("유저네임을 입력하세요");   
        }
        if ( uDto.getPassword()==null||uDto.getPassword().isEmpty()){
            throw new CustomException("패스워드를 입력하세요");
        }

        User principal = userService.로그인(uDto);
        session.setAttribute("principal", principal);

        return "redirect:/";
    }
}
