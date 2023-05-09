package com.project.blog.service;

import com.project.blog.dto.ReplySaveReqDto;
import com.project.blog.model.Board;
import com.project.blog.model.Reply;
import com.project.blog.model.User;
import com.project.blog.repository.BoardRepository;
import com.project.blog.repository.ReplyRepository;
import com.project.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public String 글쓰기(Board board, User user) {
        board.setUser(user);
        board.setCount(0);
            boardRepository.save(board);
        return "OK";
    }
    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable, String keyword){
        if(keyword ==null){
            return  boardRepository.findAll(pageable);
        }else{
            return  boardRepository.findByTitleContaining(keyword, pageable);
        }
    }
    @Transactional(readOnly = true)
    public Board 상세보기(int id){
       Board rs = boardRepository.findById(id)
               .orElseThrow(()->{return new IllegalArgumentException("글 상세보기 실패 : 아이디 찾을 수 없음.");});
       return rs;
    }
    @Transactional
    public String 글삭제(int id){
        boardRepository.deleteById(id);
        return "OK";
    }
    @Transactional
    public String 글수정(int id, Board requestBoard){

         Board board = boardRepository.findById(id).orElseThrow((() -> {
             return new IllegalArgumentException("글수정 실패 아이디 찾을 수 없음");
         }));
         board.setTitle(requestBoard.getTitle());
         board.setContent(requestBoard.getContent());

        return "OK";

    }
    @Transactional
    public String 댓글쓰기(ReplySaveReqDto replySaveReqDto){
//        User user = userRepository.findByUsername(replySaveReqDto.getUserName()).orElseThrow(()->{
//            return new IllegalArgumentException("댓글 쓰기 실패: 유저 name을 찾을 수 없습니다.");
//        }); //영
//
//        Board board = boardRepository.findById(replySaveReqDto.getBoardId()).orElseThrow(()->{
//            return new IllegalArgumentException("댓글 쓰기 실패: 게시글id를 찾을 수 없습니다.");
//        }); //영속화 완료
//
//        Reply reply = Reply.builder()
//                .user(user)
//                .board(board)
//                .content(replySaveReqDto.getContent())
//                .build();
        System.out.println(replySaveReqDto.toString());
        replyRepository.mSave(replySaveReqDto.getContent(), replySaveReqDto.getUserId(), replySaveReqDto.getBoardId(), replySaveReqDto.getColor());

        return "OK";
    }

}
