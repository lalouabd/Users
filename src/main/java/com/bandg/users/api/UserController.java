package com.bandg.users.api;

import com.bandg.users.models.User;
import com.bandg.users.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class UserController {
    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping(
            value = "/api/auth/register",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity register(@RequestBody User user)
    {

        try {
            userService.insertUser(user);
        }catch (Exception e)
        {
            return ResponseEntity.status(600).body("there was an error please try again later");
        }
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/api/auth/checkemail/{email}")
    public ResponseEntity checkEmail(@PathVariable("email") String email)
    {
        try{
            userService.loadUserByUsername(email);
        }catch (Exception e)
        {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body("email already used");
    }
    @PreAuthorize("hasAuthority('write')")
    @PutMapping("/api/auth/activateaccount")
    public ResponseEntity activateAccount(@RequestBody  String Email){
        try {
            userService.activateAccount(Email);
        }catch (Exception e)
        {
            return ResponseEntity.status(600).body("there was an error please try again later");
        }
        return ResponseEntity.status(200).build();
    }

}
