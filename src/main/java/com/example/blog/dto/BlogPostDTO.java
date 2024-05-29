package com.example.blog.dto;

import com.example.blog.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class BlogPostDTO {
    private String title;
    private String author;
    private String content;
//    private List<CommentDTO> comments;
}
