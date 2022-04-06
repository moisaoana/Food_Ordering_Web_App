package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.model.enums.UserType;
import com.example.demo.model.enums.Warning;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserType getUserType( @PathVariable String username){
        Optional<User> user= userService.findUserByUsername(username);
        return user.map(User::getType).orElse(null);
    }
    @PostMapping("/login")
    public ResponseEntity createUser(@RequestBody LoginDTO loginDTO){
        System.out.println(loginDTO.getUsername()+" "+loginDTO.getPassword());
        Warning result=userService.loginUser(loginDTO.getUsername(),loginDTO.getPassword());
        if(result==Warning.SUCCESS){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO("Successful login"));
        }else if(result==Warning.NOT_FOUND){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("Not found"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("Wrong pass"));
        }

    }
}
