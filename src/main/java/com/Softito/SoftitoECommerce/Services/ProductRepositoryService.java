package com.Softito.SoftitoECommerce.Services;

import com.Softito.SoftitoECommerce.Models.Product;
import com.Softito.SoftitoECommerce.Repositories.IRepositoryService;
import com.Softito.SoftitoECommerce.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductRepositoryService implements IRepositoryService<Product> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product add(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public String update(Long id, Product entity) {
        return null;
    }
}
