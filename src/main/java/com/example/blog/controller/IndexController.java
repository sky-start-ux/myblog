package com.example.blog.controller;

import com.example.blog.domain.Blog;
import com.example.blog.domain.User;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagsService;
import com.example.blog.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypesService typesService;
    @Autowired
    private TagsService tagsService;

    @RequestMapping("/")
    public String Index(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typesService.listTypeTop(6));
        model.addAttribute("tags",tagsService.listTags(6));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                     Pageable pageable, Model model, @RequestParam String query){
        model.addAttribute("page",blogService.listBlog(pageable,"%"+query+"%"));
        model.addAttribute("query",query);
        return "search";
    }

    @RequestMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        Blog blog = blogService.getBlog(id);
        User user = blog.getUser();
        model.addAttribute("user",user);
        return "blog";
    }


    @RequestMapping("/about")
    public String aboutMe(){
        return "about";
    }


}
