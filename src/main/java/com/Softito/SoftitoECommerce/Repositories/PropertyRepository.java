package com.Softito.SoftitoECommerce.Repositories;

import com.Softito.SoftitoECommerce.Models.Property;
import com.Softito.SoftitoECommerce.Models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Long> {
}
