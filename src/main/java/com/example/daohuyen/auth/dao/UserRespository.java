package com.example.daohuyen.auth.dao;

import com.example.daohuyen.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRespository extends JpaRepository<User,String> {
    User findByUsernameAndPasswordAndPost(String username, String password,int post);
    User findByUsername(String username);

}
