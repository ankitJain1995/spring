package com.example.Blog_App.repository;

import com.example.Blog_App.entity.Post;
import com.example.Blog_App.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("select r from Role r where r.name like :key")
   Role searchByTitle(@Param("key") String keyword);
}
