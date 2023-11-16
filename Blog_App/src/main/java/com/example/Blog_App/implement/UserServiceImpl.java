package com.example.Blog_App.implement;

import com.example.Blog_App.entity.Role;
import com.example.Blog_App.entity.User;
import com.example.Blog_App.exceptions.EmailExistsException;
import com.example.Blog_App.exceptions.ResourceNotFoundException;
import com.example.Blog_App.payloads.UserDTO;
import com.example.Blog_App.repository.RoleRepository;
import com.example.Blog_App.repository.UserRepository;
import com.example.Blog_App.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
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
        List<UserDTO> dtoList = users.stream().map(user -> userToUserDTO(user)).collect(Collectors.toList());
        return dtoList;

    }

    @Override
    public void deleteUser(int usr_id) {
    UserDTO usrDto = getUserById(usr_id);
    User user = this.dtoToUser(usrDto);
    userRepository.delete(user);

    }

    @Override
    public User addUserWithAdminRoles(User user) throws EmailExistsException {
       if(isEmailExists(user.getEmail())){
           throw new EmailExistsException("There is an account with that email address: "+user.getEmail());
       }

        User user1 = new User();
       user1.setName(user.getName());
       user1.setPassword(passwordEncoder.encode(user.getPassword()));
       user1.setEmail(user.getEmail());
       user1.setAbout(user.getAbout());
       user1.setRoles(Arrays.asList(roleRepository.searchByTitle("Role_Admin")));
       return userRepository.save(user1);

    }

    @Override
    public User addUserWithUserRoles(User user) throws EmailExistsException {
        if(isEmailExists(user.getEmail())){
            throw new EmailExistsException("There is an account with that email address: "+user.getEmail());
        }

        User user1 = new User();
        user1.setName(user.getName());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setEmail(user.getEmail());
        user1.setAbout(user.getAbout());
        user1.setRoles(Arrays.asList(roleRepository.searchByTitle("Role_User")));
        return userRepository.save(user1);
    }

    public User dtoToUser(UserDTO dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return user;
    }

    public UserDTO userToUserDTO(User user){
        UserDTO dto = new UserDTO();
       BeanUtils.copyProperties(user,dto);
        return dto;
    }

    public boolean isEmailExists(String email){
        boolean flag = false;
        Optional<User> user = userRepository.findByEmail(email);
        if(user == null){
            return flag=true;
        }
        return flag;

    }
}
