package com.example.phuotstore.repository;

import com.example.phuotstore.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT cmt FROM Comment cmt WHERE cmt.commentID =?1")
    Optional<Comment> findCommentByID(Integer brandID);

    @Query("SELECT cmt FROM Comment cmt WHERE cmt.user.userID =?1")
    Page<Comment> findCommentsByUserID(Integer userID, Pageable pageable);

    @Query("SELECT cmt FROM Comment cmt")
    Page<Comment> getAllComments(Pageable pageable);

//    @Query("SELECT cmt FROM Comment cmt WHERE cmt.status = 'SHOW' ")
//    Page<Comment> findPaginateCommentsStatusShow(Pageable pageable);
//
//    @Query("SELECT cmt FROM Comment cmt WHERE cmt.status = 'HIDDEN' ")
//    Page<Comment> findPaginateCommentsStatusHidden(Pageable pageable);
}
