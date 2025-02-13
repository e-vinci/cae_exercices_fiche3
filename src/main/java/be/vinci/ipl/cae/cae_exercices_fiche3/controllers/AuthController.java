package be.vinci.ipl.cae.cae_exercices_fiche3.controllers;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.Credentials;
import be.vinci.ipl.cae.cae_exercices_fiche3.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auths")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody Credentials credentials) {
        if (credentials == null ||
                credentials.getUsername() == null ||
                credentials.getUsername().isBlank() ||
                credentials.getPassword() == null ||
                credentials.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        boolean created = userService.register(credentials.getUsername(), credentials.getPassword());
        if (!created) throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

}
