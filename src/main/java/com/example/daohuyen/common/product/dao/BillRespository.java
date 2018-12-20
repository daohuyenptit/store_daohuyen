package com.example.daohuyen.common.product.dao;

import com.example.daohuyen.common.product.models.data.Bill;
import com.example.daohuyen.common.product.models.data.LotProduct;
import com.example.daohuyen.common.product.models.view.BillView;
import com.example.daohuyen.common.product.models.view.BillViewItem;
import com.example.daohuyen.common.product.models.view.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Set;

public interface BillRespository extends JpaRepository<Bill, String> {
    @Query("select b.lotProducts from Bill b join b.lotProducts l where b.id = ?1")
    Set<LotProduct> getBillViewItem(String billID);
    @Query("select new com.example.daohuyen.common.product.models.view.BillViewItem(b) from Bill b where b.id = ?1")
    BillViewItem getBillView(String billID);
    @Query("select count(b) from Bill b")
    int getCountBill();
    //month bestseller
    @Query("select count(b) from Bill b where  b.createDate>=?1 and  b.createDate<?2")
    int getCountMonthBill(Timestamp sd,Timestamp ed);
    @Query("select count(l) from com.example.daohuyen.common.product.models.data.LotProduct l where l.bill.createDate>=?1 and " +
            " l.bill.createDate<?2")
    int getCountMonthProduct(Timestamp sd,Timestamp ed);
    @Query("select sum(b.total) from Bill b where  b.createDate>=?1 and  b.createDate<?2")
    int getMonthTotal(Timestamp sd,Timestamp ed);
//day bestseller
    @Query("select count(b) from Bill b where  b.createDate >=?1 and b.createDate <=?2 ")
    int getCountDayBill(Timestamp sd,Timestamp ed);
    @Query("select count(l) from com.example.daohuyen.common.product.models.data.LotProduct l where l.bill.createDate>=?1 and l.bill.createDate<=?2")
    int getCountDayProduct(Timestamp sd,Timestamp ed);
    @Query("select sum(b.total) from Bill b  where  b.createDate >=?1 and b.createDate <=?2 ")
    int getDayTotal(Timestamp sd,Timestamp ed);
    @Query("select count(l) from com.example.daohuyen.common.product.models.data.LotProduct l")
    int getCountProduct();
    @Query("select sum(b.total) from Bill b")
    int getTotal();



}