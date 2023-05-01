package com.Softito.SoftitoECommerce.Services;

import com.Softito.SoftitoECommerce.Models.Property;
import com.Softito.SoftitoECommerce.Models.SubCategory;
import com.Softito.SoftitoECommerce.Repositories.IRepositoryService;
import com.Softito.SoftitoECommerce.Repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SubCategoryRepositoryService implements IRepositoryService<SubCategory> {
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Override
    public SubCategory add(SubCategory entity) {
        return subCategoryRepository.save(entity);
    }

    @Override
    public List<SubCategory> getAll() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory getById(Long id) {
        for (SubCategory subCategory : subCategoryRepository.findAll()){
            if (subCategory.getId() == id){
                return subCategory;
            }
        }
        return null;
    }

    public SubCategory getSubCategoryWithCategoryId(Long id){
        for (SubCategory subCategory : subCategoryRepository.findAll()){
            if (subCategory.getCategory().getId() == id){
                return subCategory;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public String update(Long id, SubCategory entity) {
        return null;
    }

    public Set<SubCategory> getSubCategories(Long id){
        return subCategoryRepository.findSubCategoriesAcordingToCategory(id);
    }
}
