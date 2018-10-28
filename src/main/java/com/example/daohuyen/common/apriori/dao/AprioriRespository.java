package com.example.daohuyen.common.apriori.dao;

import com.example.daohuyen.common.apriori.models.Associative;
import com.example.daohuyen.common.apriori.models.Input;
import com.example.daohuyen.common.historyOrder.models.view.LotproductView;
import com.example.daohuyen.common.product.models.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AprioriRespository  extends JpaRepository<Associative,String> {
    @Query(" SELECT ip.id FROM  com.example.daohuyen.common.apriori.models.Input ip where ip.inputcate=?1")
    List<Integer> getInput(String inputcate);

    @Query(" SELECT a.category.id FROM  com.example.daohuyen.common.apriori.models.Associative a where a.input.id=?1")
    List<String> getAllAssociative(String idinput);

    List<Associative> findByInput_Id(int inputID);
}