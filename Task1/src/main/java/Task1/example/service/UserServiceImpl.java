package Task1.example.service;

import Task1.example.config.JwtProvider;
import Task1.example.model.User;
import Task1.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements  UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserProfile(String jwt) {
        String email= JwtProvider.getEmailFromJwtToken(jwt);
        return  userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }
}
