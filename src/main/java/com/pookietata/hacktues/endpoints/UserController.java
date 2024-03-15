package com.pookietata.hacktues.endpoints;

import com.pookietata.hacktues.models.User;
import com.pookietata.hacktues.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        logger.info("Number of users retrieved: {}", users.size());
        if (users.isEmpty()) {
            logger.info("No users found in the database.");
        } else {
            users.forEach(user -> logger.info("User: {}", user.toString()));
        }
        return users;
    }
}
