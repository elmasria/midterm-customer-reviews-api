package com.udacity.course3.reviews.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    @JsonBackReference
    private Review review;

    @NotNull
    private String owner;

    @CreatedDate
    private LocalDateTime created;

    @NotNull
    private String comment;

    public Comment() {
        this.created = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getOwnerName() {
        return owner;
    }

    public void setOwnerName(String authorName) {
        this.owner = authorName;
    }

    public LocalDateTime getCreateDate() {
        return created;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.created = createDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}