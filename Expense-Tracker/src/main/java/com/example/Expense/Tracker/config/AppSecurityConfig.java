package com.example.Expense.Tracker.config;


import com.example.Expense.Tracker.repository.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;


    @Bean
SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
    http.csrf((csrf)->csrf.disable())
            .authorizeHttpRequests((requests)->
             requests.requestMatchers("/login").permitAll()
                     .requestMatchers("/register").permitAll()
                     .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());
        http.authenticationProvider(daoAuthenticationProvider());
            return http.build();
}


//    @Bean
//    public UserDetailsService userDetailsService(){
//    return new CustomUserDetailService();
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return authenticationManager();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return new AuthenticationManagerBuilder()
//                .userDetailsService(customUserDetailService)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}


//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("12345").roles("USER").build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("54321").roles("USER,ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }