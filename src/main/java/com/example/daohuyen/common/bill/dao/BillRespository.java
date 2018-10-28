package com.example.daohuyen.common.bill.dao;

import com.example.daohuyen.common.bill.models.data.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRespository extends JpaRepository<Bill, String> {

//    @Query("select b.id from Bill b")
//    List<String> getlistBillId();
}
