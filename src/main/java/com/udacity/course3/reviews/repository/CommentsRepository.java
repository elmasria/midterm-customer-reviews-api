package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentByReview_id(Integer id);
}