package shop.mtcoding.blog.util;

public class Script {
    public static String back(String msg){
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('"+msg+"');");
        sb.append("history.back();");
        return sb.toString();
    }
}
