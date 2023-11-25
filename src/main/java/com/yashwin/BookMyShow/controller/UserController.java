package com.yashwin.BookMyShow.controller;

import com.yashwin.BookMyShow.dto.UserSignUpRequestDTO;
import com.yashwin.BookMyShow.dto.UserSignupResponseDTO;
import com.yashwin.BookMyShow.models.User;
import com.yashwin.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService; // interface

    public UserSignupResponseDTO signUp(UserSignUpRequestDTO userSignUpRequestDTO){
        User user;
        UserSignupResponseDTO responseDTO = new UserSignupResponseDTO();
        try{
            UserControllerUtil.validateUserSignUPRequestDTO(userSignUpRequestDTO);
            user = userService.signUp(userSignUpRequestDTO.getName(), userSignUpRequestDTO.getEmail(), userSignUpRequestDTO.getPassword());
            // method that converts internal models into DTOs
            responseDTO.setId(user.getId());
            responseDTO.setName(user.getName());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setTickets(user.getTickets());
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS");
            return responseDTO;
        } catch (Exception e){
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("Internal Server Error");
            return responseDTO;
        }
    }
}


}
