package com.Softito.SoftitoECommerce.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "old_price")
    private Double oldPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "stock")
    private int stock;

    @Column(name = "is_status")
    private Boolean isStatus = true;

    @Column(name = "is_delete")
    private Boolean isDelete = false;

    public Product() {}
    public Product(Category category, String name, Double price, Double oldPrice, String description, String brand, int stock) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.description = description;
        this.brand = brand;
        this.stock = stock;
    }
}
