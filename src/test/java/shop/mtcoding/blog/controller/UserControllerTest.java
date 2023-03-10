package shop.mtcoding.blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void join_test() throws Exception{
        String req = "username=sss&password=1234&email=232@2323";

        ResultActions rs = mvc.perform(post("/join").content(req).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        rs.andExpect(status().is3xxRedirection());
    }

    @Test
    public void login_test() throws Exception {
        String req = "username=ssar&password=1234";

        ResultActions rs = mvc.perform(post("/login").content(req).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        rs.andExpect(status().is3xxRedirection());
    }
}
