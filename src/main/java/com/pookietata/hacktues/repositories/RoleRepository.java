package com.pookietata.hacktues.repositories;

import com.pookietata.hacktues.models.Role;
import com.pookietata.hacktues.models.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT u FROM Role u WHERE u.name = :name")
    Optional<Role> findByName(@Param("name") RoleName name);

}

