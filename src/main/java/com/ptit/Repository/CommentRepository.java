package com.ptit.Repository;

import com.ptit.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment getCommentsByIdCmt(int idCmt);

   // List<Comment> getCommentByIdCmt(int id);

    //List<Comment> getAllByIdPost(int id);
}