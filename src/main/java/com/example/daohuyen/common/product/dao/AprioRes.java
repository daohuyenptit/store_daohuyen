package com.example.daohuyen.common.product.dao;

import com.example.daohuyen.common.product.models.data.Apriori;
import com.example.daohuyen.common.product.models.data.Associative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AprioRes extends JpaRepository<Apriori,String> {
    List<Apriori> findByInput_Id(int inputID);
}
