package com.example.blog.repository;

import com.example.blog.model.BlogPost;
import com.example.blog.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    boolean existsByAuthorAndTitle(User author, String title);
    List<BlogPost> findAllByAuthor(User author);
    List<BlogPost> findAllByTitleStartingWith(String title);

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM BlogPost b WHERE b.title = :title AND b.author.id = :authorId")
//    void deleteByTitleAndAuthorId(@Param("title") String title, @Param("authorId") Long authorId);

    @Transactional
    void deleteByTitleAndAuthorId(String title, Long authorId);
}
