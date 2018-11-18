package com.example.daohuyen.common.product.dao;

import com.example.daohuyen.common.product.models.data.Bill;
import com.example.daohuyen.common.product.models.view.BillView;
import com.example.daohuyen.common.product.models.view.LotproductView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface HistoryRepository extends JpaRepository<Bill,String> {
    @Query(" SELECT new com.example.daohuyen.common.product.models.view.LotproductView(lp) FROM " +
            "com.example.daohuyen.common.product.models.data.LotProduct lp,Bill b, " +
            "com.example.daohuyen.common.customer.models.data.Customer c " +
            "WHERE lp.bill.id=b.id   AND b.customer.id=c.id AND c.id= ?1 ")
    List<LotproductView> getAllHistory1(String customerID);

    @Query(" SELECT new com.example.daohuyen.common.product.models.view.BillView(b.id,c.fullName," +
            "b.createDate,b.total,b.permit,b.receiver,b.phone,b.address_receive," +
            "b.transport,b.price_transport,b.pay) FROM Bill b join b.customer c Where c.id = ?1 and b.permit=0")
    Page<BillView> getAllBills(String customerID, Pageable pageable);
    @Query(" SELECT new com.example.daohuyen.common.product.models.view.BillView(b.id,c.fullName," +
            "b.createDate,b.total,b.permit,b.receiver,b.phone,b.address_receive," +
            "b.transport,b.price_transport,b.pay) FROM Bill b join b.customer c Where c.id = ?1 and b.permit=1")
    Page<BillView> getAllBillsSending(String customerID, Pageable pageable);
    @Query(" SELECT new com.example.daohuyen.common.product.models.view.BillView(b.id,c.fullName," +
            "b.createDate,b.total,b.permit,b.receiver,b.phone,b.address_receive," +
            "b.transport,b.price_transport,b.pay) FROM Bill b join b.customer c Where c.id = ?1 and b.permit=2")
    Page<BillView> getAllBillsSent(String customerID, Pageable pageable);
    @Query(" SELECT new com.example.daohuyen.common.product.models.view.BillView(b.id,c.fullName," +
            "b.createDate,b.total,b.permit,b.receiver,b.phone,b.address_receive," +
            "b.transport,b.price_transport,b.pay) FROM Bill b join b.customer c Where c.id = ?1 and b.permit=3")
    Page<BillView> getAllBillsCancel(String customerID, Pageable pageable);

    @Query(" SELECT new com.example.daohuyen.common.product.models.view.BillView(b.id,b.customer.fullName,b.createDate,b.total," +
            "b.permit,b.receiver,b.phone,b.address_receive, b.transport,b.price_transport,b.pay) " +
            "FROM Bill b   Where b.permit = ?1  order by b.createDate desc ")
    List<BillView> getAllBillsPermit(int permit);

    @Modifying
    @Transactional
    @Query("update Bill b set b.permit=?1 where b.id in ?2")
    void updateBill(int permit,Set<String> billID);
    @Modifying
    @Transactional
    @Query("update Bill b set b.permit=3 where b.id=?1")
    void cancelBill(String id);


}
