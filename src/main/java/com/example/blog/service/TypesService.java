package com.example.blog.service;

import com.example.blog.domain.Type;
import com.example.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypesService {
    //保存
    Type saveType(Type type);
    //查询
    Type getType(Long id);
    //分页查询
    Page<Type> listType(Pageable pageable);

    //更新
    Type updateType(Long id,Type type);
    //删除
    void deleteType(Long id);
    //根据名称查询
    Type getTypeByName(String name);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);
}
