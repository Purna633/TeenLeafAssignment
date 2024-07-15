package Task1.example.service;

import Task1.example.model.Comment;
import Task1.example.model.Post;
import Task1.example.model.User;
import Task1.example.repository.CommentRepository;
import Task1.example.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(long postId, Comment comment, User user) throws Exception {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (!postOpt.isPresent()) {
            throw new Exception("Post not found with id: " + postId);
        }
        Post post = postOpt.get();
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(long postId) throws Exception {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (!postOpt.isPresent()) {
            throw new Exception("Post not found with id: " + postId);
        }
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment updateComment(long commentId, Comment comment) throws Exception {
        Optional<Comment> commentOpt = commentRepository.findById(commentId);
        if (!commentOpt.isPresent()) {
            throw new Exception("Comment not found with id: " + commentId);
        }
        Comment existingComment = commentOpt.get();
        existingComment.setContent(comment.getContent());
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteComment(long commentId) throws Exception {
        Optional<Comment> commentOpt = commentRepository.findById(commentId);
        if (!commentOpt.isPresent()) {
            throw new Exception("Comment not found with id: " + commentId);
        }
        commentRepository.deleteById(commentId);
    }
}

