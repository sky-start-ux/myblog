package com.example.blog.controller;

import com.example.blog.domain.User;
import com.example.blog.service.UserService;
import com.example.blog.utils.CookieUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String LoginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password,
                        HttpSession session,
                        RedirectAttributes attributes){

        User user = userService.checkUser(username, password);
        if(user != null){
            user.setPassword(" ");
            session.setAttribute("user",user);
           /* int expiry = 60*60*24;
            CookieUtils.setCookie(response,"user",user.getId().toString(),expiry);*/
            return "redirect:/";
        }else {
            attributes.addFlashAttribute("message","用户名或者密码错误");
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";

    }


    @GetMapping("/logout")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registered(){
        return "registered";
    }


}
