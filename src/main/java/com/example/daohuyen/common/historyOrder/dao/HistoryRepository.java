package com.example.daohuyen.common.historyOrder.dao;

import com.example.daohuyen.common.bill.models.data.Bill;
import com.example.daohuyen.common.bill.models.data.LotProduct;
import com.example.daohuyen.common.bill.models.view.BillView;
import com.example.daohuyen.common.historyOrder.models.view.LotproductView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRepository extends JpaRepository<Bill,String> {
    @Query(" SELECT new com.example.daohuyen.common.historyOrder.models.view.LotproductView(lp) FROM com.example.daohuyen.common.bill.models.data.LotProduct lp,Bill b, com.example.daohuyen.common.customer.models.data.Customer c WHERE lp.bill.id=b.id   AND b.customer.id=c.id AND c.id= ?1 ")
    List<LotproductView> getAllHistory1(String customerID);

    @Query(" SELECT new com.example.daohuyen.common.bill.models.view.BillView(b) FROM Bill b Where b.customer.id = ?1")
            List<BillView> getAllBills(String customerID);

}
