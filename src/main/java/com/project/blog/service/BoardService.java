package com.project.blog.service;

import com.project.blog.model.Board;
import com.project.blog.model.User;
import com.project.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
