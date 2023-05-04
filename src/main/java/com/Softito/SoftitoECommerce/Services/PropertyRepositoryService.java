package com.Softito.SoftitoECommerce.Services;

import com.Softito.SoftitoECommerce.Models.Property;
import com.Softito.SoftitoECommerce.Models.SubCategory;
import com.Softito.SoftitoECommerce.Repositories.IRepositoryService;
import com.Softito.SoftitoECommerce.Repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyRepositoryService implements IRepositoryService<Property> {
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Property add(Property entity) {
        return propertyRepository.save(entity);
    }

    @Override
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property getById(Long id) {
        for (Property property : propertyRepository.findAll()){
            if (property.getId() == id){
                return property;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public String update(Long id, Property entity) {
        return null;
    }
}
