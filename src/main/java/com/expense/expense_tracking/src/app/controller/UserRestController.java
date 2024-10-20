package com.expense.expense_tracking.src.app.controller;

import com.expense.expense_tracking.src.backend.model.user.UserRequest;
import com.expense.expense_tracking.src.backend.model.user.UserResponse;
import com.expense.expense_tracking.src.backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public UserResponse login (@RequestBody UserRequest request){
        return userService.login(request);
    }
    @PostMapping("/signup")
    public UserResponse signup (@RequestBody UserRequest request){
        return userService.signup(request);
    }
    @GetMapping("/profile")
    public UserResponse userProfile (@RequestBody UserRequest request){
        return userService.userProfile(request);
    }
}
