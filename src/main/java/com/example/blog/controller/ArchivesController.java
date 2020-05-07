package com.example.blog.controller;

import com.example.blog.domain.Blog;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ArchivesController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/archives")
    public String archives(@PageableDefault(size = 8) Pageable pageable,Model model){
        model.addAttribute("page",blogService.archiveBlog(pageable));
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }
}
