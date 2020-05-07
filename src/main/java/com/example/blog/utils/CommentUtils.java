package com.example.blog.utils;

import com.example.blog.domain.Comment;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentUtils {


    //存放迭代找出的所有子代结合
    private static List<Comment> tempReplys = new ArrayList<>();

    /**
     * 循环每个顶级的节点
     * @param comments
     * @return
     */
    public static List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     */
    private static void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replyComments = comment.getReplyComments();
            for (Comment replyComment : replyComments) {
                recursively(replyComment);
            }
            //修改顶级节点的reply集合为迭代处理的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    /**
     * 递归迭代，
     * @param comment 被迭代的对象
     */
    private static void recursively(Comment comment) {
        tempReplys.add(comment);
        if(comment.getReplyComments().size() > 0){
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if(reply.getReplyComments().size() > 0){
                    recursively(reply);
                }
            }
        }

    }
}
