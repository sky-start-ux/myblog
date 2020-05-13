package com.example.blog;


import com.example.blog.domain.Comment;
import com.example.blog.domain.Tag;
import com.example.blog.domain.Type;
import com.example.blog.domain.User;
import com.example.blog.repository.*;
import com.example.blog.service.BlogService;
import com.example.blog.utils.UsersUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


import java.util.*;


@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private CommentRepository commentRepository;
    @Test
    void contextLoads() {
        Comment comment = new Comment();
        //comment.setId((long) 2);
        comment.setAvatar("/images/avatar.png");
        comment.setAdminComment(false);
        comment.setEmail("428933537@qq.com");
        comment.setCreateTime(new Date());
        comment.setNickname("sky");
        comment.setContent("你好");
        commentRepository.save(comment);
    }
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    void TestBlog(){
        User sky = userRepository.findByNickname("1111");
        System.out.println(sky);

    }



    @Test
    void TestMapper(){
       // tagsMapper.save((long) 1,(long)4);
       /* boolean b = IteratorUtils.tagsIterator(tagsMapper.findTag((long) 1),"aaaa");
        System.out.println(b);*/

       //typesMapper.save((long)1,(long)6);


    }

    @Autowired
    private TagsRepository tagsRepository;



    @Autowired
    private TypesRepository typesRepository;


    @Autowired
    BlogService blogService;

    @Test
    void repository(){
      /*  User user = new User();
        user.setId((long)2);
        List<Tag> users = tagsRepository.findByUsers(user);
        for (Tag tag : users) {
            System.out.println(tag);
        }*/
        //List<Tag> list = tagsRepository.findAll();
        //userRepository.updateUser(list,(long) 1);

       /* Tag tag = tagMapper.saveTag((long) 5, (long) 1);
        System.out.println(tag);*/

        //User one = userRepository.getOne((long) 1);
      /*  Type newType = new Type();
        newType.setName("sky");

        typesRepository.save(newType);
        System.out.println();
        typeMapper.updateType(newType.getId(),(long)1,(long)13);*/

      /*typeMapper.deleteType((long)1,(long)12);*/
        //jointTagMapper.deleteTag((long)43,(long)1);
       /* List<String> groupYear = blogRepository.findGroupYear();
        for (String s : groupYear) {
            System.out.println(s);
        }*/

    }
}
