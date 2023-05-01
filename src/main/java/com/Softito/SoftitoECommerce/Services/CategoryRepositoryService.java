package com.Softito.SoftitoECommerce.Services;

import com.Softito.SoftitoECommerce.Models.Category;
import com.Softito.SoftitoECommerce.Models.SubCategory;
import com.Softito.SoftitoECommerce.Repositories.CategoryRepository;
import com.Softito.SoftitoECommerce.Repositories.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryRepositoryService implements IRepositoryService<Category> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category add(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        for (Category category : categoryRepository.findAll()){
            if (category.getId() == id){
                return category;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public String update(Long id, Category entity) {
        return null;
    }

    public Category findCategoryByName(String categoryName){
        return categoryRepository.findCategoryByName(categoryName);
    }
}
