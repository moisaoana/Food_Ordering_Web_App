package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String index(){
        return "start";
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getFirstName()+" "+userDTO.getLastName()+" "+userDTO.getUsername()+" "+userDTO.getPassword()+" "+userDTO.getType());
        boolean result=userService.insertUser(userDTO);
        if(result) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO("User created"));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Username exists!"));
        }
    }

}
