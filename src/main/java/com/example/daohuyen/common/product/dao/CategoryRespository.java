package com.example.daohuyen.common.product.dao;

import com.example.daohuyen.common.product.models.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRespository extends JpaRepository<Category,String> {

    @Query("select new com.example.daohuyen.common.product.models.view.CategoryViewModel(c) " +
            "from Category c")
    List<Category> getAllCategoryViewModels();
}