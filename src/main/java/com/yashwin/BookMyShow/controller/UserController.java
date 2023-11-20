package com.yashwin.BookMyShow.controller;

import com.yashwin.BookMyShow.dto.UserSignUpRequestDTO;
import com.yashwin.BookMyShow.dto.UserSignupResponseDTO;
import com.yashwin.BookMyShow.models.User;
import com.yashwin.BookMyShow.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserSignupResponseDTO signUp(UserSignUpRequestDTO userSignUpRequestDTO){
        User user;
        UserSignupResponseDTO userSignupResponseDTO = new UserSignupResponseDTO();
        try{

        }catch(Exception e){

        }

    }


}
