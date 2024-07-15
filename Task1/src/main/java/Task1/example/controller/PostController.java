package Task1.example.controller;

import Task1.example.model.Comment;
import Task1.example.model.Post;
import Task1.example.model.User;
import Task1.example.service.PostService;
import Task1.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;



    @PostMapping()
    public Post createPost(@RequestBody Post post,
                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserProfile(jwt);
        post.setUser(user);
        Post createdPost = postService.createPost(post, user);
        return createdPost;
    }

    @PutMapping("/update/{id}")
    public Post updatePost(@RequestBody Post post, @PathVariable long id) throws Exception {
        Post updatedPost = postService.updatePost(post, id);
        return updatedPost;
    }

    @GetMapping("/all-post")
    public List<Post> getAllPost() throws Exception {
        List<Post> posts = postService.findAllPost();
        return posts;
    }

    @DeleteMapping("/delete/{postId}")
    public String deletePost(@PathVariable long postId) throws Exception {
        postService.deletePost(postId);
        return "Post deleted successfully";
    }

    @PutMapping("/{id}/like")
    public Post likePost(@RequestHeader("Authorization") String jwt,
                         @PathVariable long id) throws Exception {
        
        User user = userService.getUserProfile(jwt);
        Post likedPost = postService.likePost(id, user);
        return likedPost;
    }

    @GetMapping("/{postId}/comments")
    public List<Comment> getCommentsForPost(@PathVariable long postId) throws Exception {
        return postService.getCommentsForPost(postId);
    }


}
