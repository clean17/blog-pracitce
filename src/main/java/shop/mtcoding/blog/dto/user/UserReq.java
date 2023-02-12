package shop.mtcoding.blog.dto.user;

import javax.annotation.Generated;

import lombok.Getter;
import lombok.Setter;

public class UserReq {
    
    @Setter
    @Getter
    public static class UserJoinReqDto{
        private String username;
        private String password;
        private String email;
    }
}
