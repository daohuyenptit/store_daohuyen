package com.example.daohuyen.common.bill.dao;

import com.example.daohuyen.common.bill.models.data.Bill;
import com.example.daohuyen.common.bill.models.data.LotProduct;
import com.example.daohuyen.common.historyOrder.models.view.LotproductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Set;

public interface BillRespository extends JpaRepository<Bill, String> {
@Query("select b.lotProducts from Bill b join b.lotProducts l where b.id = ?1")
Set<LotProduct> getBillViewItem(String billID);
}