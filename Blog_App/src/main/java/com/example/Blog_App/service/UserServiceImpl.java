package com.example.Blog_App.service;

import com.example.Blog_App.entity.User;
import com.example.Blog_App.exceptions.ResourceNotFoundException;
import com.example.Blog_App.payloads.UserDTO;
import com.example.Blog_App.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User newUser = dtoToUser(userDTO);
        User usr = userRepository.save(newUser);
        UserDTO dto = this.userToUserDTO(usr);
        return dto;

    }

    @Override
    public UserDTO updateUser(UserDTO usr, int usr_id) {
        UserDTO usrDto = getUserById(usr_id);
        usrDto.setName(usr.getName() !=null ? usr.getName() : usrDto.getName());
        usrDto.setEmail(usr.getEmail() != null ? usr.getEmail() : usrDto.getEmail());
        usrDto.setPassword(usr.getPassword() != null ? usr.getPassword() : usrDto.getPassword());
        usrDto.setAbout(usr.getAbout() !=null ? usr.getAbout() : usrDto.getAbout());
        User newUser = this.dtoToUser(usrDto);
        return userToUserDTO(userRepository.save(newUser));

    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id "+id));
        return userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDTO> usrDto = users.stream().map(user -> (this.userToUserDTO(user))).collect(Collectors.toList());
        return usrDto;

    }

    @Override
    public void deleteUser(int usr_id) {
    UserDTO usrDto = getUserById(usr_id);
    User user = this.dtoToUser(usrDto);
    userRepository.delete(user);

    }

    public User dtoToUser(UserDTO dto){
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setAbout(dto.getAbout());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserDTO userToUserDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAbout(user.getAbout());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
