package com.example.demo.mapper;

import com.example.demo.dto.LoginDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginMapper {

    public LoginDTO convertToDTO(User user){
        LoginDTO loginDTO=new LoginDTO();
        loginDTO.setUsername(user.getUsername());
        loginDTO.setPassword(user.getPassword());
        return loginDTO;
    }

}
