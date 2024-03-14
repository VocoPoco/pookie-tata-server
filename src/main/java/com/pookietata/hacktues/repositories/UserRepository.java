package com.pookietata.hacktues.repositories;


import com.pookietata.hacktues.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.summonerName = :summonerName")
    Optional<User> findUserBySummonerName(String summonerName);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsBySummonerName(String username);
}