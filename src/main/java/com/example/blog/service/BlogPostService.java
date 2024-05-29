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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> check(String author, String title){
        Optional<User> user = userRepository.findByUsername(author);
        if(user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        boolean exists = blogPostRepository.existsByAuthorAndTitle(user.get(), title);
        if(exists){
            return new ResponseEntity<>("Title Not Available", HttpStatus.CONFLICT);
        }
        else
            return new ResponseEntity<>("Title Available", HttpStatus.OK);
    }

    public ResponseEntity<String> save(BlogPostDTO blogPost) {
        BlogPost blogPostEntity = new BlogPost();
        saveBlog(blogPostEntity, blogPost);
        return new ResponseEntity<>("Blog saved", HttpStatus.CREATED);
    }

    public ResponseEntity<String> delete(String author, String title){
        Optional<User> user = userRepository.findByUsername(author);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        blogPostRepository.deleteByTitleAndAuthorId(title, user.get().getId());
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


    private void saveBlog(BlogPost blogPostEntity, BlogPostDTO blogPostDTO) {
        Optional<User> user = userRepository.findByUsername(blogPostDTO.getAuthor());
        if(user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        blogPostEntity.setAuthor(user.get());
        blogPostEntity.setTitle(blogPostDTO.getTitle());
        blogPostEntity.setContent(blogPostDTO.getContent());
        blogPostRepository.save(blogPostEntity);
    }

    public ResponseEntity<List<BlogPostDTO>> getAll(String author) {
        Optional<User> user = userRepository.findByUsername(author);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        List<BlogPost> blogPosts = blogPostRepository.findAllByAuthor(user.get());
        List<BlogPostDTO> blogPostDTOs = covertIntoBlogPostDTOs(blogPosts);
        return new ResponseEntity<>(blogPostDTOs, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<BlogPostDTO>> getAll(){
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        List<BlogPostDTO> blogPostDTOs = covertIntoBlogPostDTOs(blogPosts);
        return new ResponseEntity<>(blogPostDTOs, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<BlogPostDTO>> getAllByTitle(String title) {
        List<BlogPost> blogPosts = blogPostRepository.findAllByTitleStartingWith(title);
        List<BlogPostDTO> blogPostDTOs = covertIntoBlogPostDTOs(blogPosts);
        return new ResponseEntity<>(blogPostDTOs, HttpStatus.ACCEPTED);
    }

    private List<BlogPostDTO> covertIntoBlogPostDTOs(List<BlogPost> blogPosts) {
        List<BlogPostDTO> blogPostDTOs = new ArrayList<>();
        for (BlogPost blogPost : blogPosts) {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setAuthor(blogPost.getAuthor().getUsername());
            blogPostDTO.setTitle(blogPost.getTitle());
            blogPostDTO.setContent(blogPost.getContent());
            blogPostDTOs.add(blogPostDTO);
        }
        return blogPostDTOs;
    }

}
