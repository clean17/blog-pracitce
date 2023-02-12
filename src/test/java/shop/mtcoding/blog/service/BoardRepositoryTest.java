package shop.mtcoding.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.blog.model.BoardRepository;

@MybatisTest
public class BoardRepositoryTest {
    
    @Autowired
    private BoardRepository boardRepository;

    
    @Autowired
    ObjectMapper om;

    @Test
    public void findAllWithUser_test() throws Exception{
        
        ObjectMapper om = new ObjectMapper();

        List<BoardMainRespDto> blist = boardRepository.findAllWithUser();
        String a = om.writeValueAsString(blist);
        System.out.println(a);
        // assertThat(blist.size()).isEqualTo(7);
        // assertThat(blist.get(1).getUsername()).isEqualTo("love");
        
    }
    
}
