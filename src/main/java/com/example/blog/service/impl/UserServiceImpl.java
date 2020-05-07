package com.example.blog.service.impl;

import com.example.blog.domain.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import com.example.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByNicknameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public User checkIsUser(String nickname) {

        return userRepository.findByNickname(nickname);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.getOne(userId);
    }

}
