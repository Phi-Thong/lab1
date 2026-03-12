package com.poly.Controller;

import com.poly.dao.UserDAO;
import com.poly.ennity.J6user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping("/login/{action}")
    public String login(Model model, @PathVariable("action") String action) {

        List<J6user> users = userDAO.findAll();

        System.out.println("===== DANH SÁCH USER TRONG DATABASE =====");
        for (J6user u : users) {
            System.out.println("Email: " + u.getUsername() + " | Password: " + u.getPassword());
        }

        if(action.equals("success")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            System.out.println("===== USER ĐANG LOGIN =====");
            System.out.println("Email: " + auth.getName());
            System.out.println("Roles: " + auth.getAuthorities());
        }

        switch(action) {
            case "form" -> model.addAttribute("message", "Vui lòng đăng nhập");
            case "success" -> model.addAttribute("message", "Đăng nhập thành công");
            case "failure" -> model.addAttribute("message", "Sai thông tin đăng nhập");
            case "exit" -> model.addAttribute("message", "Đăng xuất thành công");
        }

        return "login";
    }
}