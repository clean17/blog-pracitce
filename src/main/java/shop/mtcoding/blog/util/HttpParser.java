package shop.mtcoding.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpParser {
    
    public static String thumbnail(String html){
       
        String img = "";
        Document doc = Jsoup.parse(html);
        Elements els = doc.select("img"); 
        if (els.size() == 0){ 
           return "/images/default_profile.png";
        }else{ 
            Element el = els.get(0);
            img += el.attr("src"); 
            return img;
        }
    }
        
}
