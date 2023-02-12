package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardRepository {
    public List<Board> findAll();
    public Board findById(int id);
    public int insert(
        @Param("title") String title,
        @Param("content") String content,
        @Param("user_id") int userId
    );
    public int update(
        @Param("title") String title,
        @Param("content") String content
    );
    public int delete(int id);
}