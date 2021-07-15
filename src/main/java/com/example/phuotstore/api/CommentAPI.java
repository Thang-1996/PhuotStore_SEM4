package com.example.phuotstore.api;

import com.example.phuotstore.model.Comment;
import com.example.phuotstore.model.User;
import com.example.phuotstore.repository.CommentRepository;
import com.example.phuotstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/comments")
public class CommentAPI {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping //read data
    public ResponseEntity<Page<Comment>> getAllComments(Pageable pageable) {
        return ResponseEntity.ok(commentRepository.getAllComments(pageable));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<Comment>> getCommentsByUserID(@PathVariable int id, Pageable pageable) {
        Optional<User> optionalUser = userRepository.findUserByID(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(commentRepository.findCommentsByUserID(id, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentByID(@PathVariable int id) {
        Optional<Comment> optionalComment = commentRepository.findCommentByID(id);
        if (!optionalComment.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalComment.get());
    }

//    @GetMapping("/hidden")
//    public ResponseEntity<Page<Comment>> getCommentsByStatusHidden(Pageable pageable) {
//        return ResponseEntity.ok(commentRepository.findPaginateCommentsStatusHidden(pageable));
//    }
//
//    @GetMapping("/show")
//    public ResponseEntity<Page<Comment>> getCommentsByStatusShow(Pageable pageable) {
//        return ResponseEntity.ok(commentRepository.findPaginateCommentsStatusShow(pageable));
//    }
}
