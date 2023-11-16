package com.example.Blog_App.service;

import com.example.Blog_App.entity.Role;
import com.example.Blog_App.entity.User;
import com.example.Blog_App.exceptions.EmailExistsException;
import com.example.Blog_App.payloads.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, int usr_id);

    UserDTO getUserById(int usr_id);

    List<UserDTO> getAllUsers();

    void deleteUser(int usr_id);

    public User addUserWithAdminRoles(User user) throws EmailExistsException;

    public User addUserWithUserRoles(User user) throws EmailExistsException;
}
