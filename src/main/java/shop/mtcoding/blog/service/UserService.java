package shop.mtcoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.blog.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.blog.ex.CustomException;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.model.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(UserJoinReqDto uDto){
        try {
            userRepository.insert(uDto.getUsername(), uDto.getPassword(), uDto.getEmail());
        } catch (Exception e) {
            throw new CustomException("회원가입에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 무조건 try catch 로 해야 테스트에서 에러가 나오는데 ?
    }

    public User 로그인(UserLoginReqDto uDto) {
        User principal = userRepository.findByUsernameAndPassword(uDto.getUsername(), uDto.getPassword());
        if( principal == null ){
            throw new CustomException("아이디 혹은 비밀번호가 다릅니다.");
        }

        return principal;

    };
}
