package com.example.SpringRoleBasedAuthentication.repository;

import com.example.SpringRoleBasedAuthentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
