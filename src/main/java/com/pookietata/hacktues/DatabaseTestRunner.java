package com.pookietata.hacktues;

import com.pookietata.hacktues.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTestRunner implements CommandLineRunner {

    private final UserRepository userRepository; // Example repository

    public DatabaseTestRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Perform a database operation, like fetching users
        userRepository.findAll().forEach(user -> System.out.println(user.toString()));
    }
}
