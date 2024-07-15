package Task1.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne
    private User user;
    private String image;
    private String description;
    private LocalDateTime createdAt;
    private List<Long> likes=new ArrayList<>();
    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}

