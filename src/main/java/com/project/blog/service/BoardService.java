package com.project.blog.service;

import com.project.blog.model.Board;
import com.project.blog.model.User;
import com.project.blog.repository.BoardRepository;
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

}
