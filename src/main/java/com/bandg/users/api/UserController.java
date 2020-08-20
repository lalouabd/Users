package com.bandg.users.api;

import com.bandg.users.models.User;
import com.bandg.users.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class UserController {
    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping(
            value = "/api/auth/register",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody User user)
    {
        System.out.println("reg");
        userService.insertUser(user);
        return "ok";
    }
}
