package com.Softito.SoftitoECommerce.Controllers;

import com.Softito.SoftitoECommerce.Models.Product;
import com.Softito.SoftitoECommerce.Models.Property;
import com.Softito.SoftitoECommerce.Models.SubCategory;
import com.Softito.SoftitoECommerce.Services.ProductRepositoryService;
import com.Softito.SoftitoECommerce.Services.PropertyRepositoryService;
import com.Softito.SoftitoECommerce.Services.SubCategoryRepositoryService;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    ProductRepositoryService productRepositoryService;
    @Autowired
    PropertyRepositoryService propertyRepositoryService;
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



    @GetMapping("/product-detail/{id}")
    public String getProductDetail(@PathVariable Long id, Model model){
        Product product = productRepositoryService.getById(id);

        try {
            for (Property property : product.getProperties()){
                System.out.println(property.getSubCategory().getProperty() + ": " + property.getPropertyField());
            }
        }catch (Exception e){
            System.out.println("bir hata var");
        }
        int percentage = (int) ((( product.getOldPrice() - product.getPrice()) / product.getPrice()) * 100);
        model.addAttribute("product",product);
        model.addAttribute("percentage",percentage);
        return "front2/product-detail";
    }

}
