package com.project.blog.repository;

import com.project.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //생략가능
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
