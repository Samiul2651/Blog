package com.example.blog.repository;

import com.example.blog.model.BlogPost;
import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    boolean existsByAuthorAndTitle(User author, String title);
    void deleteByAuthorAndTitle(User author, String title);
}
