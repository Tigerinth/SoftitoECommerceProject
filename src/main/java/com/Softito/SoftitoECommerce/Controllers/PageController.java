package com.Softito.SoftitoECommerce.Controllers;

import com.Softito.SoftitoECommerce.Models.Product;
import com.Softito.SoftitoECommerce.Models.SubCategory;
import com.Softito.SoftitoECommerce.Repositories.ProductRepository;
import com.Softito.SoftitoECommerce.Services.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class PageController {
    @Autowired
    private ProductRepositoryService service;
    @GetMapping("/admin/panel")
    public String getAdminPanel() {
        return "back/index";
    }

    @GetMapping("/admin/tables")
    public String getAdminTable(){
        return "back/tables";
    }

    @GetMapping("/user/index")
    public String getUserIndex(){
        return "front/index-2";
    }

    @GetMapping("front/cart")
    public String getCart(){
        return "front/cart";
    }

    @GetMapping("front/cart-2")
    public String getLogin(){
        return "front/cart-2";
    }

    @GetMapping("")
    public String listProducts(Model model) {
        List<Product> listProducts= service.getAll();
        List <Product> ek = new ArrayList<>();
        for (Product i : listProducts) {
            if(i.getIsDelete() == false) {
                ek.add(i);
            }
        }
        model.addAttribute("listProducts",ek);
        return "test";
    }
    @GetMapping("/admin/add-product/{id}")
    public String geturun(Model model,@PathVariable("id") Long id){
        model.addAttribute("product",service.getById(id));
        return "productdetail";
    }

}
