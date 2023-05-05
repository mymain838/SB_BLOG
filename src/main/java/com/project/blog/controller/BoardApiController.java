package com.project.blog.controller;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.dto.ResponseDto;
import com.project.blog.model.Board;
import com.project.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<String> delete(@PathVariable int id){
          String rs = boardService.글삭제(id);
          return new ResponseDto<String>(HttpStatus.OK.value(), rs);
    }
    @PutMapping("/api/board/{id}")
    public ResponseDto<String> update(@PathVariable int id, @RequestBody Board board){
        System.out.println(id);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
      String rs =  boardService.글수정(id, board);
        return new ResponseDto<String>(HttpStatus.OK.value(), rs);
    }
}
