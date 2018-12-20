package com.example.daohuyen.common.product.dao;

import com.example.daohuyen.common.product.models.body.ItemBody;
import com.example.daohuyen.common.product.models.data.Product;
import com.example.daohuyen.common.product.models.view.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ProductRespository extends JpaRepository<Product,String> {
    @Query("select new Product(p.id,p.name, p.price, p.description, p.createdDate, p.logoUrl, p.category) from Product p where p.id=?1")
    Product getProduct(String productID);
    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id) " +
            "from Product p")
    Page<ProductViewModel> getAllProductViewModels(Pageable pageable);
    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id) from Product p where p.category.id = ?1")
    Page<ProductViewModel> getAllProductByCategory(Pageable pageable, String categoryID);

    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id) from Product p where p.id = ?1")
    ProductViewModel getProductViewModel(String productID);
    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id) from Product p where p.name  like ?1")
    Page<ProductViewModel> getAllProductBySearch(Pageable pageable, String key);
    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id) from Product p  where p.category.title  like ?1")
    Page<ProductViewModel> getAllProductBySearchNameCategory(Pageable pageable, String key);

    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id) from Product p where p.name  like ?1")
    Page<ProductViewModel> getProductAssociative(Pageable pageable, String key);


    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id) from Product p where p.category.id = ?1")
    Page<ProductViewModel> getAllProductListByCategory(Pageable pageable,String categoryID);
    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id) " +
            "from Product p ")
    Page<ProductViewModel> get30ProductNew(Pageable pageable);
    @Query("select new com.example.daohuyen.common.product.models.body.ItemBody(p.name,sum(l.amount)) from com.example.daohuyen.common.product.models.data.LotProduct l  join l.bill b  left join l.product p where b.permit=1  group by p.name having  sum(l.amount)>5")
    Set<ItemBody> getAllBestSellerProduct();
    @Query("select new com.example.daohuyen.common.product.models.body.ItemBody(p.name,sum(l.amount)) from " +
            "com.example.daohuyen.common.product.models.data.LotProduct l  join l.bill b  left join " +
            "l.product p where b.permit=1 and b.createDate >=?1 and b.createDate<?2 group by p.name having  sum(l.amount)>5")
    Set<ItemBody> getAllBestSellerMonthProduct(Timestamp sd,Timestamp ed);
    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id)  from " +
            "com.example.daohuyen.common.product.models.data.LotProduct l  join l.bill b  left join " +
            "l.product p where b.permit=1 and b.createDate >=?1 and b.createDate<?2 group by p having  sum(l.amount)>5")
    List<Product> getBestSellerProduct(Timestamp sd, Timestamp ed);
    @Query("select new com.example.daohuyen.common.product.models.body.ItemBody(p.name,sum(l.amount)) from " +
            "com.example.daohuyen.common.product.models.data.LotProduct l  join l.bill b  left join " +
            "l.product p where b.permit=1 and b.createDate >=?1 and b.createDate <=?2  group by p.name having  sum(l.amount)>=2 ")
    Set<ItemBody> getAllBestSellerDayProduct(Timestamp sd,Timestamp ed);

    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p.id, p.name, p.price, p.logoUrl, p.description, p.category.id) from Product p where p.createdDate<?1")
    Page<ProductViewModel> getAllProductInventory(Pageable pageable,Timestamp time);




//    @Query("select new com.example.daohuyen.common.product.models.view.ProductViewModel(p) from Product p where MATCH (p.name)  AGAINST (?1 IN NATURAL LANGUAGE MODE) ")
//    Page<ProductViewModel> getAllProductBySearch(Pageable pageable, String key);

}
