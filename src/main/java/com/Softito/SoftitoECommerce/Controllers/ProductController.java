package com.Softito.SoftitoECommerce.Controllers;

import com.Softito.SoftitoECommerce.Models.*;
import com.Softito.SoftitoECommerce.Repositories.ImageRepository;
import com.Softito.SoftitoECommerce.Repositories.ProductRepository;
import com.Softito.SoftitoECommerce.Repositories.UserRepository;
import com.Softito.SoftitoECommerce.Services.CategoryRepositoryService;
import com.Softito.SoftitoECommerce.Services.ProductRepositoryService;
import com.Softito.SoftitoECommerce.Services.PropertyRepositoryService;
import com.Softito.SoftitoECommerce.Services.SubCategoryRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Controller
public class ProductController {
    @Autowired
    private ProductRepositoryService productRepositoryService;
    @Autowired
    private CategoryRepositoryService categoryRepositoryService;
    @Autowired
    SubCategoryRepositoryService subCategoryRepositoryService;
    @Autowired
    PropertyRepositoryService propertyRepositoryService;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/add-product")
    public String adminAddProduct(Model model){
        model.addAttribute("categories",categoryRepositoryService.getAll());
        return "back/add-product";
    }

    @GetMapping("/admin/select-category")
    public String adminSelectCategory(Model model){
        model.addAttribute("categories",categoryRepositoryService.getAll());
        return "back/select-category";
    }


    @GetMapping("/admin/add-product/{id}")
    public String adminAddProduct(Model model,@PathVariable("id") Long id){
        Set<SubCategory> subCategorySet = subCategoryRepositoryService.getSubCategories(id);

        model.addAttribute("subCategories",subCategorySet);
        model.addAttribute("category",categoryRepositoryService.getById(id));
        return "back/add-product";
    }



    @PostMapping("/admin/add-product/{id}")
    public String adminAddProductPost(Model model,RedirectAttributes redirAttrs,HttpServletRequest request,
                                      @RequestParam("images[]") MultipartFile[] images,
                                      @PathVariable("id") Long id,
                                      @RequestParam("name") String name,
                                      @RequestParam("brand") String brand,
                                      @RequestParam("price") Double price,
                                      @RequestParam("oldPrice") Double oldPrice,
                                      @RequestParam("stock") int stock,
                                      @RequestParam("properties[]") String[] properties,
                                      @RequestParam("description") String description){
        Category category = categoryRepositoryService.getById(id);
        Product newProduct = new Product(category,upperCase(name),price,oldPrice,upperCase(description),upperCase(brand),stock);
        productRepositoryService.add(newProduct);
        int i=0;
        for (SubCategory subCategory : subCategoryRepositoryService.getSubCategories(id)){
            Property newProperty = new Property();
            newProperty.setPropertyField(upperCase(properties[i++]));
            newProperty.setProduct(newProduct);
            newProperty.setSubCategory(subCategory);
            propertyRepositoryService.add(newProperty);
        }

        //Resim ekleme işlemleri

            try {
                for (MultipartFile file : images) {
                BufferedImage image = ImageIO.read(file.getInputStream());

                String directory = System.getProperty("user.dir") + "/src/main/resources/static/images/";

                String imageName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "_" + new Random().nextInt(1000) + ".jpg" ;
                File outputFile = new File(directory + imageName);

                ImageIO.write(image, "jpg", outputFile);
                Image imageEntity = new Image();
                imageEntity.setImagePath("/images/" + imageName);
                imageEntity.setProduct(newProduct);
                imageRepository.save(imageEntity);
                }
            } catch (Exception e) {
                System.out.println("Resim işleminde bir hata oluştu");
                e.printStackTrace();
            }


        //model.addAttribute("subCategories",subCategorySet);
        redirAttrs.addFlashAttribute("success", "Ürün başarıyla eklendi");
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/get/subcategoryies/{id}")
    public Set<SubCategory> getSubCategoryies(@PathVariable Long id){
        return subCategoryRepositoryService.getSubCategories(id);
    }

    public String upperCase(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    @GetMapping("/ImageUpload")
    public String img(Model model) {
        return "ImageTest";
    }


}
