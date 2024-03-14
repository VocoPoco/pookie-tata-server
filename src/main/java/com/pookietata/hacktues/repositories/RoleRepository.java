package com.pookietata.hacktues.repositories;

import com.pookietata.hacktues.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT u FROM Role u WHERE u.name = :name")
    Optional<Role> findByName(String name);
}
