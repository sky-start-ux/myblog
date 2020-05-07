package com.example.blog.service;

import com.example.blog.domain.Comment;

import java.util.List;


public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
