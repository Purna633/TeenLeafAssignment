package Task1.example.service;

import Task1.example.model.Comment;
import Task1.example.model.Post;
import Task1.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
public interface PostService {
    public Post createPost(Post recipe, User user);
    public Post findPostById(long id) throws Exception;

    public void deletePost(long id) throws  Exception;
    public Post updatePost(Post recipe, long Id) throws  Exception;
    public List<Post> findAllPost();
    public Post likePost(long recipeId,User user) throws Exception;
    List<Comment> getCommentsForPost(long postId) throws Exception;
}
