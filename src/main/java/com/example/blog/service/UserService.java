package com.example.blog.service;

import com.example.blog.domain.User;

public interface UserService {

    public User checkUser(String username,String password);

    User checkIsUser(String nickname);

    User save(User user);

    User getUser(Long userId);
}
