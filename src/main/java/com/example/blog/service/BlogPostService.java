package com.example.blog.service;

import com.example.blog.dto.BlogPostDTO;
import com.example.blog.model.BlogPost;
import com.example.blog.model.User;
import com.example.blog.repository.BlogPostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private UserRepository userRepository;



    public ResponseEntity<String> save(BlogPostDTO blogPost) {
        BlogPost blogPostEntity = new BlogPost();
        saveBlog(blogPostEntity, blogPost);
        return new ResponseEntity<>("Blod saved", HttpStatus.CREATED);
    }
//
    private void saveBlog(BlogPost blogPostEntity, BlogPostDTO blogPostDTO) {
        Optional<User> user = userRepository.findByUsername(blogPostDTO.getAuthor());
        if(user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        blogPostEntity.setAuthor(user.get());
        blogPostEntity.setTitle(blogPostDTO.getTitle());
        blogPostEntity.setContent(blogPostDTO.getContent());
    }
}
