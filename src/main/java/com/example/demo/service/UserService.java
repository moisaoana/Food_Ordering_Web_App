package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.enums.Warning;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class defines the service methods for the User class.
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 25.04.2022
 */
@Service
public class UserService {
    private final static Logger LOGGER = Logger.getLogger(UserService.class.getName());
    /**
     * Access the DB through UserRepository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * For encrypting the passwords in the DB
     */
    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    /**
     * For converting UserDTO objects into User objects and vice versa
     */
    private UserMapper userMapper;

    public UserService(){
        userMapper=new UserMapper();
        LOGGER.setLevel(Level.INFO);
    }

    /**
     * Method that checks if the username provided by a client at registration has not already been used
     * @param userDTO user information provided by the client at registration
     * @return true (if the username is unique) or false (otherwise)
     */
    public boolean isUsernameUnique(UserDTO userDTO){
        LOGGER.info("Checking if username is unique");
        List<User> allUsers=userRepository.findAll();
        for(User u:allUsers){
            if(u.getUsername().equals(userDTO.getUsername())){
                LOGGER.warning("Duplicate username provided!");
                return false;
            }
        }
        LOGGER.info("Correct username provided!");
        return true;
    }

    /**
     * Method to insert a new User object into the database.
     * @param userDTO user information needed to create the User object
     * @return true(if insertion succeeded) or false (otherwise)
     */
    public boolean insertUser(UserDTO userDTO){
        LOGGER.info("Trying to insert user in DB");
        if(isUsernameUnique(userDTO)){
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            User user=userMapper.convertFromDTO(userDTO);
            userRepository.save(user);
            LOGGER.info("Successful insertion");
            return true;
        }else{
            LOGGER.warning("Insertion failed!");
            return false;
        }

    }

    /**
     * Method to find users in the database based on their username
     * @param username a String representing the username of the user we are looking for
     * @return optionally returns a User object
     */
    public Optional<User> findUserByUsername(String username){
        LOGGER.info("Finding user with username: "+username);
        return userRepository.findByUsername(username);
    }

    /**
     * Method for checking the credentials of a user at login.
     * @param username a String representing the username provided by the client
     * @param password a String representing the password provided by the client
     * @return Warning.NOT_FOUND (if the username doesn't exist in the DB) or
     *         Warning.WRONG_PASS (if the provided password doesn't match the one retrieved from the DB) or
     *         Warning.SUCCESS (if the credentials are correct)
     */
    public Warning loginUser(String username, String password){
        LOGGER.info("Login user "+username);
       Optional<User> user=findUserByUsername(username);
       if(!user.isPresent()){
           LOGGER.warning("User with username "+username+" not found!");
           return Warning.NOT_FOUND;
       }else{
           if(bCryptPasswordEncoder.matches(password,user.get().getPassword())){
               LOGGER.info("Successful login");
               return Warning.SUCCESS;
           }else{
               LOGGER.warning("Wrong password provided by the client!");
               return Warning.WRONG_PASS;
           }
       }
    }
}
