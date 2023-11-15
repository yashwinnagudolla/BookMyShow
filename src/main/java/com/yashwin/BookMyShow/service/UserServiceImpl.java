package com.yashwin.BookMyShow.service;

import com.yashwin.BookMyShow.exception.InvalidCredentialException;
import com.yashwin.BookMyShow.exception.UserAlreadyExistsException;
import com.yashwin.BookMyShow.exception.UserNotFoundException;
import com.yashwin.BookMyShow.models.User;
import com.yashwin.BookMyShow.repository.UserRepository;

import java.util.Optional;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    public User login(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User with given email does not exist");
        }
        User user = userOptional.get();
        if(user.getPassword().equals(password)){
            return user;
        }else{
            throw new InvalidCredentialException("Credentials are Invalid");
        }

    }
    public User signUp(String name,String email,String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User with given email already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        return userRepository.save(user);
    }
}
