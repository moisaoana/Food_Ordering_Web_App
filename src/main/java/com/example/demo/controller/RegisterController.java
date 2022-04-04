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

    @GetMapping("/register")
    public String index(){
        return "start";
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getFirstName()+" "+userDTO.getLastName()+" "+userDTO.getUsername()+" "+userDTO.getPassword()+" "+userDTO.getType());
       // userService.insertUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("User created"));
    }

}
