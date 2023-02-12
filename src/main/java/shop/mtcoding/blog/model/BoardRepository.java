package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blog.dto.board.BoardResp.BoardDetailResqDto;
import shop.mtcoding.blog.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.blog.dto.board.BoardResp.BoardUpdateResqDto;

@Mapper
public interface BoardRepository {
    public List<Board> findAll();
    public Board findById(int id);
    public List<BoardMainRespDto> findAllWithUser();
    public BoardDetailResqDto findByIdWithUser(int id);
    public BoardUpdateResqDto findByIdWithUserUpdate(int id);
    public int insert(
        @Param("title") String title,
        @Param("content") String content,
        @Param("userId") int userId
    );
    public int update(
        @Param("title") String title,
        @Param("content") String content,
        @Param("id") int id
    );
    public int delete(int id);
}
