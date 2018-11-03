package com.example.daohuyen.common.customer.dao;

import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.customer.models.view.CustomerView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerRespository extends JpaRepository<Customer, String> {
    Customer findByUser_Id(String userID);

    @Query("select new com.example.daohuyen.common.customer.models.view.CustomerView(c) from Customer c " +
            "where c.fullName= ?1")
    List<CustomerView> getListCustomerView(String name);
    @Query("select new Customer(c.id,c.fullName, c.phone, c.genderID, c.address, c.identityCard, c.description,c.avatarUrl, c.birthday, c.email,c.user) from Customer c where c.id= ?1")
    Customer getCustomerByID(String customerID);

}
