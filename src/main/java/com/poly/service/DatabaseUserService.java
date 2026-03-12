package com.poly.service;

import com.poly.dao.UserDAO;
import com.poly.ennity.J6user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        J6user user = userDAO.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("===== USER LOGIN TỪ DATABASE =====");
        System.out.println("Email: " + user.getUsername());
        System.out.println("Password DB: " + user.getPassword());

        return User.withUsername(user.getUsername())
                .password(user.getPassword()) // {noop}123
                .roles("USER")
                .build();
    }
}