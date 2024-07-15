package Task1.example.service;

import Task1.example.model.Comment;
import Task1.example.model.Post;
import Task1.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {
    Comment createComment(long postId, Comment comment, User user) throws Exception;

    List<Comment> getCommentsByPostId(long postId) throws Exception;

    Comment updateComment(long commentId, Comment comment) throws Exception;

    void deleteComment(long commentId) throws Exception;
}
