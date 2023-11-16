package com.example.Blog_App.controller;

import com.example.Blog_App.entity.JwtResponse;
import com.example.Blog_App.entity.LoginModel;
import com.example.Blog_App.security.CustomUserDetailService;
import com.example.Blog_App.service.UserService;
import com.example.Blog_App.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginModel login) throws Exception{
        authenticate(login.getEmail(),login.getPassword());

        //We need to generate the JWT Token

        final UserDetails userDetails = customUserDetailService.loadUserByUsername(login.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);

    }

    private void authenticate(String email, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }
        catch(DisabledException e){
            throw new Exception("User Disabled");
        }
        catch (BadCredentialsException ex){
            throw new Exception("Bad Credentials");
        }
    }

}
