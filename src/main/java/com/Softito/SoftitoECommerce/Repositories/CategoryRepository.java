package com.Softito.SoftitoECommerce.Repositories;

import com.Softito.SoftitoECommerce.Models.Admin;
import com.Softito.SoftitoECommerce.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c FROM Category c WHERE c.categoryName = ?1")
    public Category findCategoryByName(String categoryName);
}
