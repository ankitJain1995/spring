package com.example.Expense.Tracker.service;

import com.example.Expense.Tracker.exception.ItemAlreadyExistsException;
import com.example.Expense.Tracker.exception.ResourceNotFoundException;
import com.example.Expense.Tracker.model.User;
import com.example.Expense.Tracker.model.UserModel;
import com.example.Expense.Tracker.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    public User addUser(UserModel usr){
   if(userRepository.existsByEmail(usr.getEmail())){
       throw new ItemAlreadyExistsException("User already registered with this email "+ usr.getEmail());
   }
    User newUser = new User();
        BeanUtils.copyProperties(usr,newUser);
       newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public User readUserById(Long id)  {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with the following id "+id));
    }

    public User updateUser(User usr,Long id) throws ResourceNotFoundException{
        User oUser = readUserById(id);
        oUser.setName(usr.getName() !=null ? usr.getName() : oUser.getName());
        oUser.setEmail(usr.getEmail() != null ? usr.getEmail() : oUser.getEmail());
        oUser.setPassword(usr.getPassword() != null ? bcryptEncoder.encode(usr.getPassword()) : oUser.getPassword());
        oUser.setAge(usr.getAge() !=0 ? usr.getAge() : oUser.getAge());
       return userRepository.save(oUser);
    }

    public void removeUser(Long id){
        User user = readUserById(id);
        userRepository.delete(user);
    }


}
