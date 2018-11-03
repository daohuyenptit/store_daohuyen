package com.example.daohuyen.admin.dao;

import com.example.daohuyen.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRespository extends JpaRepository<User,String> {
    User findAdminByUsernameAndPasswordAndPost(String username,String password,int post);
}
