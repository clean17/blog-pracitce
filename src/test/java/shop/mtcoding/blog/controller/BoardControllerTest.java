package shop.mtcoding.blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog.dto.board.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.blog.model.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private MockHttpSession mockSession;
    @BeforeEach
    public void setUp(){
        User principal = new User();
        principal.setId(1);
        principal.setUsername("ssar");
        principal.setPassword("1234");
        principal.setEmail("ssar@nate.com");
        mockSession.setAttribute("principal", principal);
    }

    @Test
    public void save_test() throws Exception{
        String req = "title=안녕&content=하세요";

        ResultActions rs = mvc.perform(post("/board/write")
            .content(req)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .session(mockSession)
            );
        rs.andExpect(status().is3xxRedirection());
    }

    @Test
    public void main_test() throws Exception {
        String req = "username=ssar&password=1234";

        ResultActions rs = mvc.perform(post("/login").content(req).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        rs.andExpect(status().is3xxRedirection());
    }

    @Test
    public void deleteBoard_test() throws Exception {
        int id =4;
        ResultActions rs = mvc.perform(delete("/board/delete/"+id).session(mockSession));
        String a = rs.andReturn().getResponse().getContentAsString();
        System.out.println(a);
    }
    @Test
    public void updateForm_test() throws Exception {
        int id=1;
        ResultActions rs = mvc.perform(get("/board/updateForm/"+id)
        .session(mockSession)
        );
        rs.andExpect(status().isOk());
    }
    @Test
    public void updateBoard_test() throws Exception {
        ObjectMapper om = new ObjectMapper();
        BoardUpdateReqDto b = new BoardUpdateReqDto();
        b.setTitle(" 그러면");
        b.setContent("이렇게");
        int id = 1;
        String req = om.writeValueAsString(b);
        ResultActions rs = mvc.perform(put("/board/update/"+id)
        .content(req)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .session(mockSession)
        );
        rs.andExpect(status().isOk());

    }
}
