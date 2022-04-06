package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.enums.Warning;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserService(){
        userMapper=new UserMapper();
    }

    public boolean isUsernameUnique(UserDTO userDTO){
        List<User> allUsers=userRepository.findAll();
        for(User u:allUsers){
            if(u.getUsername().equals(userDTO.getUsername())){
                return false;
            }
        }
        return true;
    }
    public boolean insertUser(UserDTO userDTO){
        if(isUsernameUnique(userDTO)){
            User user=userMapper.convertFromDTO(userDTO);
            userRepository.save(user);
            return true;
        }else{
            return false;
        }

    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Warning loginUser(String username, String password){
       Optional<User> user=findUserByUsername(username);
       if(!user.isPresent()){
           return Warning.NOT_FOUND;
       }else{
           if(user.get().getPassword().equals(password)){
               return Warning.SUCCESS;
           }else{
               return Warning.WRONG_PASS;
           }
       }
    }
}
