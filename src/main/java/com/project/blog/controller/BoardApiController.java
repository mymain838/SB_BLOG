package com.project.blog.controller;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.dto.ResponseDto;
import com.project.blog.model.Board;
import com.project.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class BoardApiController {
    @Autowired
    private BoardService boardService;


    @PostMapping("/api/board")
    public ResponseDto<String> save(@RequestBody Board board , @AuthenticationPrincipal PrincipalDetail principal){
           String rs = boardService.글쓰기(board,  principal.getUser());
            return new ResponseDto<String>(HttpStatus.OK.value(), rs);

    }
}
