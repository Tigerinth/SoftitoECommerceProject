package com.Softito.SoftitoECommerce.Controllers;

import com.Softito.SoftitoECommerce.Models.Category;
import com.Softito.SoftitoECommerce.Models.SubCategory;
import com.Softito.SoftitoECommerce.Services.CategoryRepositoryService;
import com.Softito.SoftitoECommerce.Services.SubCategoryRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepositoryService categoryRepositoryService;
    @Autowired
    private SubCategoryRepositoryService subCategoryRepositoryService;

    @GetMapping("/admin/add-category")
    public String adminAddCategory(Model model){
        return "back/add-category";
    }

    @PostMapping("/admin/add-category")
    public String admingAddCategoryPost(HttpServletRequest request, Model model, RedirectAttributes redirAttrs, @RequestParam("categoryName") String categoryName, @RequestParam("properties[]") String[] properties){
        Category newCategory = new Category(upperCase(categoryName));
        categoryRepositoryService.add(newCategory);
;
        for (String property : properties){
            System.out.println(upperCase(property));
            if (property!=null || property!=""){
                SubCategory subCategory = new SubCategory();
                subCategory.setProperty(upperCase(property));
                subCategory.setCategory(newCategory);
                subCategoryRepositoryService.add(subCategory);
            }
        }
        redirAttrs.addFlashAttribute("success", "Kategori başarıyla eklendi");
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
    public String upperCase(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
