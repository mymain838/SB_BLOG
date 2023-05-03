package com.project.blog.repository;

import com.project.blog.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //생략가능
public interface BoardRepository extends JpaRepository<Board, Integer> {
    public Page<Board> findByTitleContaining(String keyword,Pageable pageable);

}
