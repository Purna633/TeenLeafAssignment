package Task1.example.controller;
import Task1.example.model.Comment;
import Task1.example.model.User;
import Task1.example.service.CommentService;
import Task1.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;



    @PostMapping("/{postId}")
    public Comment createComment(@PathVariable long postId,
                                 @RequestBody Comment comment,
                                 @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserProfile(jwt);
        comment.setUser(user);
        Comment createdComment = commentService.createComment(postId, comment, user);
        return createdComment;
    }

    @GetMapping("/get-comment/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable long postId) throws Exception {
        return commentService.getCommentsByPostId(postId);
    }

    @PutMapping("/update/{commentId}")
    public Comment updateComment(@PathVariable long commentId,
                                 @RequestBody Comment comment) throws Exception {
        return commentService.updateComment(commentId, comment);
    }

    @DeleteMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable long commentId) throws Exception {
        commentService.deleteComment(commentId);
        return "Comment deleted successfully";
    }
}
