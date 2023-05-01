package com.Softito.SoftitoECommerce.Repositories;

import com.Softito.SoftitoECommerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
