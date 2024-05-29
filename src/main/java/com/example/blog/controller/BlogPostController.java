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
@CrossOrigin(origins = "http://localhost:4200/")
public class BlogPostController {
    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/check/{author}/{title}")
    public ResponseEntity<String> checkBlogTitle(@PathVariable String author, @PathVariable String title){
        return blogPostService.check(author, title);
    }

    @GetMapping("/getAllUser/{author}")
    public ResponseEntity<List<BlogPostDTO>> getAll(@PathVariable String author){
        return blogPostService.getAll(author);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BlogPostDTO>> getAll(){
        return blogPostService.getAll();
    }

    @PutMapping("/save")
    public ResponseEntity<String> saveBlogPost(@RequestBody BlogPostDTO blogPost){
        return blogPostService.save(blogPost);
    }

    @DeleteMapping("/delete/{author}/{title}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable String author, @PathVariable String title){
        return blogPostService.delete(author, title);
    }

    @GetMapping("search/{title}")
    public ResponseEntity<List<BlogPostDTO>> getAllBlogPostByTitle(@PathVariable String title){
        return blogPostService.getAllByTitle(title);
    }
}
