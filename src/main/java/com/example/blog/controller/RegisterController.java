package com.example.blog.controller;

import com.example.blog.domain.User;
import com.example.blog.service.UserService;
import com.example.blog.utils.FileUploadUtils;
import com.example.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

public class RegisterController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public String Registered(@RequestParam("nickname") String nickname,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("email") String  email,
                             @RequestParam("avatar") MultipartFile  avatar,
                             RedirectAttributes attributes) {

        User user = new User();
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(MD5Utils.code(password));
        user.setEmail(email);
        if(userService.checkIsUser(nickname) == null){

            String filePath = FileUploadUtils.getPath(avatar, nickname,1);
            user.setAvatar(filePath);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userService.save(user);
            return "login";
        }

        attributes.addFlashAttribute("message","该用户已存在！");
        return "redirect:/admin/register";



    }
}
