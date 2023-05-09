package com.project.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplySaveReqDto {
    private int boardId;
    private int userId;
    private String content;
    private String color;
}
