package com.example.Blog_App.security;

import com.example.Blog_App.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse respose, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String userName = null;

        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){

            jwtToken = requestTokenHeader.substring(7);

            try{
                userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
            }
            catch(IllegalArgumentException e){
                throw new RuntimeException("Unable to get JWT Token");
            }
            catch(ExpiredJwtException ex){
                throw new RuntimeException("JWT Token has expired");
            }
        }

        //Once we get the token validate the token
        if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = userDetailService.loadUserByUsername(userName);
            if(jwtTokenUtil.validateToken(jwtToken, userDetails)){
               // UsernamePasswordAuthenticationToken ae = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,respose);

    }
}
