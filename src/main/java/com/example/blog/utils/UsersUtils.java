package com.example.blog.utils;

import com.example.blog.domain.Tag;
import com.example.blog.domain.Type;
import com.example.blog.domain.User;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class UsersUtils {

    public static Tag tagsUser(Tag tag, User user){
        List<Tag> tags = user.getTags();
        tags.add(tag);
        user.setTags(tags);
        List<User> users = tag.getUsers();
        users.add(user);
        tag.setUsers(users);
        return tag;
    }

    public static Type TypesUser(Type type, User user){
        List<Type> types = user.getTypes();
        types.add(type);
        user.setTypes(types);
        List<User> users = type.getUsers();
        users.add(user);
        type.setUsers(users);
        return type;
    }
}
