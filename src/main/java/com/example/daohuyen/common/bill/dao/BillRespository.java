package com.example.daohuyen.common.bill.dao;

import com.example.daohuyen.common.bill.models.data.Bill;
import com.example.daohuyen.common.bill.models.view.BillView;
import com.example.daohuyen.common.bill.models.view.BillViewItem;
import com.example.daohuyen.common.product.models.view.ProductViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRespository extends JpaRepository<Bill, String> {
@Query("select new com.example.daohuyen.common.bill.models.view.BillViewItem(b) from Bill b where b.id = ?1")
BillViewItem getBillViewItem(String billID);
}