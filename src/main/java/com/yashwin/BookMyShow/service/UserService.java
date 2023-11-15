package com.yashwin.BookMyShow.service;


import com.yashwin.BookMyShow.models.User;

public interface UserService {
    User login(String email,String password);
    User signUp(String name,String email,String password);
}
