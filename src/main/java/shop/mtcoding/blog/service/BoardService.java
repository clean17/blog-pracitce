package shop.mtcoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blog.dto.board.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.blog.ex.CustomApiException;
import shop.mtcoding.blog.ex.CustomException;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;




    @Transactional
    public void 글쓰기(BoardSaveReqDto bDto, int userId) {
        try {
            boardRepository.insert(bDto.getTitle(), bDto.getContent(), userId);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Transactional
    public void 글삭제(int id, int principalId) {
        Board board = boardRepository.findById(id);
        
        if ( board.getUserId() != principalId){
            throw new CustomApiException("글을 삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        try {
            boardRepository.delete(id);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void 글수정(int id, BoardUpdateReqDto bdto, int principalId) {
        Board board = boardRepository.findById(principalId);
        if (board.getUserId() != principalId){
            throw new CustomApiException( "글을 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        try {
            boardRepository.update(
                bdto.getTitle(),
                bdto.getContent(),
                principalId);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
