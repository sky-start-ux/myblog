package com.example.blog.controller;

import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/footer")
public class FooterController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "about :: newblogList";
    }
    @RequestMapping("/admin-tags")
    public String adminTags(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "admin-tags :: newblogList";
    }
    @RequestMapping("/admin-types")
    public String adminTypes(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "admin-types :: newblogList";
    }
    @RequestMapping("/archives")
    public String archives(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "archives :: newblogList";
    }
    @RequestMapping("/blog")
    public String blog(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "blog :: newblogList";
    }
    @RequestMapping("/blogs")
    public String blogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "blogs :: newblogList";
    }
    @RequestMapping("/blogs-input")
    public String blogsInput(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "blogs-input :: newblogList";
    }
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "index :: newblogList";
    }
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "login :: newblogList";
    }
    @RequestMapping("/login-index")
    public String loginIndex(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "registered :: newblogList";
    }
    @RequestMapping("/search")
    public String search(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "search :: newblogList";
    }
    @RequestMapping("/tags")
    public String tags(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "tags :: newblogList";
    }
    @RequestMapping("/tags-input")
    public String tagsInput(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "tags-input :: newblogList";
    }
    @RequestMapping("/types")
    public String types(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "types :: newblogList";
    }
    @RequestMapping("/types-input")
    public String newBlog(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "types-input :: newblogList";
    }
}
