package com.example.daohuyen.common.customer.dao;

import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.customer.models.data.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRespository extends JpaRepository<Gender, Integer> {
}
