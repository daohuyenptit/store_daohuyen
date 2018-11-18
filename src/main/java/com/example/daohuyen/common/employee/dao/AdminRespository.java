package com.example.daohuyen.common.employee.dao;

import com.example.daohuyen.common.customer.models.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRespository extends JpaRepository<User,String> {
    User findAdminByUsernameAndPasswordAndPost(String username, String pass,int pos);
}
