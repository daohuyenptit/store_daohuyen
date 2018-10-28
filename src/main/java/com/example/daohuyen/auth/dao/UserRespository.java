package com.example.daohuyen.auth.dao;

import com.example.daohuyen.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,String> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

}
