package com.example.daohuyen.common.employee.dao;

import com.example.daohuyen.common.employee.models.response.InformationShop;
import com.example.daohuyen.common.employee.models.view.InformationShopView;
import com.example.daohuyen.common.product.models.view.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InformationResposity extends JpaRepository<InformationShop,String> {
    @Query("select new com.example.daohuyen.common.employee.models.view.InformationShopView(p.description,p.address,p.hotline) " +
            "from InformationShop p order by p.createdDate DESC ")
    List<InformationShopView> getInformationShop();
    @Query("select p from InformationShop p order by p.createdDate DESC ")
    List<InformationShop> getShop();

}
