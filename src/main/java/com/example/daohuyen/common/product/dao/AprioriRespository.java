package com.example.daohuyen.common.product.dao;

import com.example.daohuyen.common.product.models.data.Apriori;
import com.example.daohuyen.common.product.models.data.Associative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AprioriRespository  extends JpaRepository<Associative,String> {
    @Query(" SELECT ip.id FROM  com.example.daohuyen.common.product.models.data.Input ip where ip.inputcate=?1")
    List<Integer> getInput(String inputcate);

    @Query(" SELECT a.category.id FROM  com.example.daohuyen.common.product.models.data.Associative a where a.input.id=?1")
    List<String> getAllAssociative(String idinput);

    List<Associative> findByInput_Id(int inputID);
}