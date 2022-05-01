package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.model.enums.UserType;
import com.example.demo.model.enums.Warning;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.JwtUtils;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class LoginController {
    private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    JwtUtils jwtUtils=new JwtUtils();

    @GetMapping("/login/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserType getUserType( @PathVariable String username){
        LOGGER.info("GET method for finding the type of the user "+username);
        Optional<User> user= userService.findUserByUsername(username);
        return user.map(User::getType).orElse(null);
    }
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginDTO loginDTO){
        LOGGER.info("POST method for login user "+ loginDTO.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()).get(0);
        LOGGER.info("Generated token for user: "+jwt);
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
        /*
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

         */

    }
}
