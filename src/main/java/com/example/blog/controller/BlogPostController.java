package com.example.blog.controller;

import com.example.blog.dto.BlogPostDTO;
import com.example.blog.service.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/blogPost/")
public class BlogPostController {
    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/check/{id}/{title}")
    public ResponseEntity<String> checkBlogTitle(@PathVariable Long id, @PathVariable String title){
        return blogPostService.check(id, title);
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<BlogPostDTO>> getAll(@PathVariable Long id){
        return blogPostService.getAll(id);
    }

    @PutMapping("/save")
    public ResponseEntity<String> saveBlogPost(@RequestBody BlogPostDTO blogPost){
        return blogPostService.save(blogPost);
    }

    @PostMapping("/delete/{id}/{title}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable Long id, @PathVariable String title){
        return blogPostService.delete(id, title);
    }
}
