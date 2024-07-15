package Task1.example.service;

import Task1.example.model.Comment;
import Task1.example.model.Post;
import Task1.example.model.User;
import Task1.example.repository.CommentRepository;
import Task1.example.repository.PostRepository;
import Task1.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class PostServiceImpl implements  PostService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Post createPost(Post post, User user) {
        Post createdPost=new Post();
        createdPost.setTitle(post.getTitle());
        createdPost.setImage(post.getImage());
        createdPost.setDescription(post.getDescription());
        createdPost.setUser(user);
        createdPost.setCreatedAt(LocalDateTime.now());
        return postRepository.save(createdPost);
    }

    @Override
    public Post findPostById(long id) throws Exception {
        Optional<Post> opt=postRepository.findById(id);
        if(opt.isPresent())
        {
            return opt.get();
        }
        throw new Exception("post not found found with this is"+id);
    }


    @Override
    public void deletePost(long id) throws Exception {
        findPostById(id);
        postRepository.deleteById(id);

    }

    @Override
    public Post updatePost(Post post, long Id) throws Exception {
        Post oldPost=findPostById(Id);
        if(post.getTitle()!=null){
            oldPost.setTitle(post.getTitle());
        }
        if(post.getImage()!=null)
        {
            oldPost.setImage(post.getImage());
        }
        if(post.getDescription()!=null)
        {
            oldPost.setDescription(post.getDescription());
        }

        return postRepository.save(oldPost);
    }


    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post likePost(long postId, User user) throws Exception {
        Post post=findPostById(postId);
        if(post.getLikes().contains(user.getId())){
            post.getLikes().remove(user.getId());
        }
        else{
            post.getLikes().add(user.getId());
        }
        return postRepository.save(post);
    }

    @Override
    public List<Comment> getCommentsForPost(long postId) throws Exception {
        Post post = findPostById(postId);
        return commentRepository.findByPostId(postId);
    }
}

