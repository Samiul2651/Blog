package com.example.blog.controller;

import com.example.blog.dto.BlogPostDTO;
import com.example.blog.service.BlogPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-blog/")
public class BlogPostController {
    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PutMapping("/save")
    public ResponseEntity<String> saveBlogPost(@RequestBody BlogPostDTO blogPost){
        return blogPostService.save(blogPost);
    }
}
