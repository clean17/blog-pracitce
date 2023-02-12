package shop.mtcoding.blog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class HttpParserTest {
    
    @Test
    public void jsoup_test2() { 
        String html = "<p>123123<img src=\"data:image/jpeg;base64,/9j/4AAQSkZJRgAB\"><img src=\"data:image/jpeg;base64,/9j/34fghZJRgAB\"><img src=\"data:image/jpeg;base64,/9j/4shxcvhJRgAB\"></p>";                
        String img = "";
        Document doc = Jsoup.parse(html);
        Elements els = doc.select("img"); 
        if (els.size() == 0){ 
            // 임시 사진 제공
        }else{ 
            Element el = els.get(0);
            img += el.attr("src"); 
            System.out.println("테스트"+img); 
        }
    }
}
