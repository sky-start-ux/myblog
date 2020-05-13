package com.example.blog.service.impl;


import com.example.blog.domain.Blog;
import com.example.blog.domain.BlogQuery;
import com.example.blog.domain.Type;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import com.example.blog.utils.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository repository;
    @Override
    public Blog getBlog(Long id) {
        return repository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return repository.findAll(new Specification<Blog>() {
            /**
             *
             * @param root 查询的对象
             * @param criteriaQuery 查询条件的容器
             * @param criteriaBuilder  模糊查询
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%" + blog.getTitle()));
                }
                if (blog.getTypeId() != null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                if(blog.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setCreateTime(repository.getOne(blog.getId()).getCreateTime());
            blog.setViews(repository.getOne(blog.getId()).getViews());
            blog.setUpdateTime(new Date());
        }
        return repository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog blog1 = repository.getOne(id);
        if(blog1 == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog1,blog);
        return blog1;
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return repository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return repository.findTop(pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, String query) {

        return repository.findByQuery(pageable,query);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = repository.getOne(id);
        if(blog == null){
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        if (b.getFirstPicture().equals("") ){
            b.setFirstPicture("/images/touxiang.jpg");
        }
        String content = b.getContent();
        String HtmlContent = MarkdownUtils.markdownToHtmlExtensions(content);
        b.setContent(HtmlContent);
        repository.updateViews(id);
        return b;
    }

    @Override
    public Map<String, Page<Blog>> archiveBlog(Pageable pageable) {
        List<String> years = repository.findGroupYear();
        Map<String,Page<Blog>> map = new HashMap<>();
        for (String year : years) {
            //System.out.println(year);
            Page<Blog> list = repository.findByYear(pageable, year);
            map.put(year,list);
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return repository.count();
    }
}
