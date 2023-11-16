package com.example.Blog_App.implement;

import com.example.Blog_App.entity.Role;
import com.example.Blog_App.repository.RoleRepository;
import com.example.Blog_App.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role addRoles(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
       return roleRepository.searchByTitle("%"+name+"%");
    }

}
