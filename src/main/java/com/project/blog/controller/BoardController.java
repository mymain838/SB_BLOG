package com.project.blog.controller;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.model.Board;
import com.project.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    //@AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, String keyword) {

        Page<Board> rs = boardService.글목록(pageable,keyword);

        int startPage = ((pageable.getPageNumber()) / 10 ) * 10 + 1;
        int endPage = startPage + 10 - 1  < rs.getTotalPages() ? startPage + 10 - 1 : rs.getTotalPages() ;
        System.out.println(startPage);
        System.out.println(endPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards", rs);
        model.addAttribute("keyword", keyword);
        return "index"; // viewReslover 작동!!
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
