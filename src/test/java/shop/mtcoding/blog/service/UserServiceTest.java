package shop.mtcoding.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import shop.mtcoding.blog.model.UserRepository;

@MybatisTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    // 여기서 테스트 못하는건가 보다 컨트롤러 테스트에서 서비스 익셉션이 나옴
    
    // String username ;
    // String password = "";
    // String email = "";

    // @Test
    // public void insert_test() throws Exception{
    //     int result = userRepository.insert(username, password, email);
        
    //     assertThat(result).isEqualTo(1);
    // }
}
