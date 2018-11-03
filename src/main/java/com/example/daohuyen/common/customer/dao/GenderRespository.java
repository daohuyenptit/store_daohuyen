package com.example.daohuyen.common.customer.dao;

import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.customer.models.data.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenderRespository extends JpaRepository<Gender, Integer> {
    @Query("select new Gender(g.id,g.name) from Gender g where g.id= ?1")
    Gender getGenderID(int genderID);
}
