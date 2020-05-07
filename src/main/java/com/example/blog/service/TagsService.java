package com.example.blog.service;

import com.example.blog.domain.Tag;
import com.example.blog.domain.Type;
import com.example.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagsService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTags(Pageable pageable);


    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);

    Tag getTagByName(String name);


    List<Tag> listTags();

    List<Tag> listTags(String Ids);

    List<Tag> listTags(Integer size);

}
