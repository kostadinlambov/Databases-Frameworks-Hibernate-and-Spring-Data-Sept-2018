package application.services;

import application.models.User;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void registerUser(User user) {
        Long id = user.getId();

        if (id != null) {
            if (this.userRepository.exists(id)) {
                throw new RuntimeException("Duplicate user ids");
            }
        }

        String username = user.getUsername();

        if (username != null) {
            User byUsername = this.userRepository.findByUsername(username);

            if (byUsername != null) {
                throw new RuntimeException("Duplicate usernames");
            }
        }

        this.userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id);
    }
}
