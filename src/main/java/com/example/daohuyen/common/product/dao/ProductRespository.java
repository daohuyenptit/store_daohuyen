package com.example.daohuyen.common.product.dao;

import com.example.daohuyen.common.product.models.data.Product;
import com.example.daohuyen.common.product.models.view.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRespository extends JpaRepository<Product,String> {

    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p) " +
            "from Product p")
    Page<ProductViewModel> getAllProductViewModels(Pageable pageable);
    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p) from Product p where p.category.id = ?1")
    Page<ProductViewModel> getAllProductByCategory(Pageable pageable, String categoryID);

    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p) from Product p where p.id = ?1")
    ProductViewModel getProductViewModel(String productID);
    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p) from Product p where p.name  like ?1")
    Page<ProductViewModel> getAllProductBySearch(Pageable pageable, String key);

    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p) from Product p where p.name  like ?1")
    Page<ProductViewModel> getProductAssociative(Pageable pageable, String key);

    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p) from Product p where p.category.id = ?1")
    List<ProductViewModel> getAllProductListByCategory(String categoryID);


//    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p) from Product p where MATCH (p.name)  AGAINST (?1 IN NATURAL LANGUAGE MODE) ")
//    Page<ProductViewModel> getAllProductBySearch(Pageable pageable, String key);

}