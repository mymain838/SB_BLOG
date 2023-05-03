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
    public Page<Board> 글목록(Pageable pageable){

        return boardRepository.findAll(pageable);
    }
}
