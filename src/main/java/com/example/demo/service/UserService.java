package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserService(){
        userMapper=new UserMapper();
    }

    public void insertUser(UserDTO userDTO){
        User user=userMapper.convertFromDTO(userDTO);
        userRepository.save(user);
    }
}
