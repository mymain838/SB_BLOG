package com.project.blog.repository;

import com.project.blog.dto.ReplySaveReqDto;
import com.project.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    @Modifying
    @Query(value = "insert into reply(content,userId,boardId,createDate,color) values(?1,?2,?3,now(),?4)",nativeQuery = true)
    int mSave(String content, int userId, int boardId, String color);
}
