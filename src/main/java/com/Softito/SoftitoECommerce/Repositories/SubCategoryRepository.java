package com.Softito.SoftitoECommerce.Repositories;

import com.Softito.SoftitoECommerce.Models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    @Query("SELECT sc FROM SubCategory sc WHERE sc.category.id = ?1")
    public Set<SubCategory> findSubCategoriesAcordingToCategory(Long category_Id);
}
