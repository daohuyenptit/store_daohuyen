package com.example.daohuyen.common.apriori.models.view;

import com.example.daohuyen.common.product.models.data.Category;

import java.util.ArrayList;
import java.util.Set;

public class CategoryCollectionView {

    private int id;
    Set<Category> categories;

    public CategoryCollectionView() {
    }

    public CategoryCollectionView(int id, Set<Category> categories) {
        this.id = id;
        this.categories = categories;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
