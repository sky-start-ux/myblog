package com.example.blog.service.impl;

import com.example.blog.domain.Tag;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.TagsRepository;
import com.example.blog.service.TagsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsRepository repository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return repository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return repository.getOne(id);
    }



    @Transactional
    @Override
    public Page<Tag> listTags(Pageable pageable) {
        return repository.findAll(pageable);
    }



    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {//tag更新填写的新标签

        Tag tags = repository.findByName(repository.getOne(id).getName());//查询出来需要废弃的旧标签
        if(tags == null){
            throw new NotFoundException("不存在该标签");
        }


        BeanUtils.copyProperties(tag,tags);

        return repository.save(tags);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
      repository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return repository.findByName(name);
    }



    @Override
    public List<Tag> listTags() {
        return repository.findAll();
    }

    @Override
    public List<Tag> listTags(String Ids) {
        List<Long> list = convertToList(Ids);
        return repository.findAllById(list);
    }

    @Override
    public List<Tag> listTags(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return repository.findTop(pageable);
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids != null){
            String[] split = ids.split(",");
            for (int i = 0;i <split.length; i++){
                list.add(new Long(split[i]));
            }
        }
        return list;
    }
}
