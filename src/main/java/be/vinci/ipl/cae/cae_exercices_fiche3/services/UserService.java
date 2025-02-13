package be.vinci.ipl.cae.cae_exercices_fiche3.services;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.User;
import be.vinci.ipl.cae.cae_exercices_fiche3.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) return false;

        createOne(username, password);

        return true;
    }

    public void createOne(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("USER");
        userRepository.save(user);
    }

}
