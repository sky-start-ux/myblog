package com.example.blog.service.impl;


import com.example.blog.domain.Tag;
import com.example.blog.domain.Type;
import com.example.blog.domain.User;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.TypesRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.TypesService;
import com.example.blog.utils.UsersUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypesServiceImpl implements TypesService {
    @Autowired
    private TypesRepository typesRepository;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    @Override
    public Type saveType(Type type) {

        return typesRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typesRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typesRepository.findAll(pageable);
    }


    @Transactional
    @Override
    public Type updateType(Long id, Type type) {

        Type userType = typesRepository.getOne(id);
        if(userType == null){
            throw  new NotFoundException("不存在该类型");
        }

        BeanUtils.copyProperties(type,userType);

        return typesRepository.save(userType);

    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typesRepository.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typesRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typesRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);

        return typesRepository.findTop(pageable);
    }
}
