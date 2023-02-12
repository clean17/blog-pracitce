package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blog.dto.board.BoardResp.BoardMainRespDto;

@Mapper
public interface BoardRepository {
    public List<Board> findAll();
    public Board findById(int id);
    public List<BoardMainRespDto> findAllWithUser();
    public int insert(
        @Param("title") String title,
        @Param("content") String content,
        @Param("userId") int userId
    );
    public int update(
        @Param("title") String title,
        @Param("content") String content
    );
    public int delete(int id);
}
