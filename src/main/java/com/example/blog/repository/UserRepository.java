package com.example.blog.repository;

import com.example.blog.domain.Tag;
import com.example.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByNicknameAndPassword(String nickname, String password);

    User findByNickname(String nickname);


}
