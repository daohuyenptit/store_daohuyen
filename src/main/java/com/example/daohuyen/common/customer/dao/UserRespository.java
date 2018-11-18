package com.example.daohuyen.common.customer.dao;

import com.example.daohuyen.common.customer.models.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRespository extends JpaRepository<User,String> {
    User findByUsernameAndPasswordAndPost(String username, String password,int post);
    User findByUsername(String username);

}
