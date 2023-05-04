package com.Softito.SoftitoECommerce.Controllers;

import com.Softito.SoftitoECommerce.Models.Product;
import com.Softito.SoftitoECommerce.Models.User;
import com.Softito.SoftitoECommerce.Repositories.ProductRepository;
import com.Softito.SoftitoECommerce.Repositories.UserRepository;
import com.Softito.SoftitoECommerce.Services.ProductRepositoryService;
import com.Softito.SoftitoECommerce.Services.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private ProductRepositoryService productRepositoryService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/login")
    public String getLogin(Model model){
        return "front2/login";
    }
    Long x = 0L;
    @PostMapping("login")
    public String userLoginPost(Model model, RedirectAttributes redirAttrs,@RequestParam("email") String email, @RequestParam("password") String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        for(User user : userRepositoryService.getAll()){
            if (user.getEmail().matches(email) && passwordEncoder.matches(password, user.getPassword())) {
                x = user.getId();
                System.out.println(x);
                return "redirect:/homepage";
            }
        }

        redirAttrs.addFlashAttribute("error", "Böyle bir kullanıcı bulunamadı!");
        return "redirect:/login";
    }
    @GetMapping("/homepage")
    public String getHomePage(Model model){
        model.addAttribute("products",productRepositoryService.getAll());
        model.addAttribute("x",x);
        return "front2/index";
    }
    @GetMapping("/anasayfa")
    public String anasayfa(Model model) {
        model.addAttribute("products",productRepositoryService.getAll());
        return "front2/anasayfa";
    }


    @GetMapping("/register")
    public String getRegister(Model model){
        return "front2/register";
    }
    @PostMapping("/register")
    public String userRegisterPost(Model model, RedirectAttributes redirAttrs, @RequestParam("address") String address, @RequestParam("lName") String lName, @RequestParam("fName") String fName, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("buraya geldim");
        if (password.equals(confirmPassword)) {
            if (fName != null && lName != null && password != null && email != null) {
                try {
                    User user = new User(email, fName, lName, encodedPassword, address,0D);
                    userRepositoryService.add(user);
                    return "redirect:/login";
                } catch (Exception ex) {
                    redirAttrs.addFlashAttribute("error", "Böyle bir kullanıcı zaten var!");
                    return "redirect:/register";
                }
            } else {
                redirAttrs.addFlashAttribute("error", "Bir Hata Oluştu!");
                return "redirect:/register";
            }
        } else {
            redirAttrs.addFlashAttribute("error", "Şifreler Uyuşmadı!");
            return "redirect:/register";
        }
    }
    @GetMapping("/addmoney/user{uid}/{para}")
    public String addmoneytest(Model model,@PathVariable("uid")Long uid, @PathVariable("para") Long para){
        User user = userRepository.getById(uid);
        user.setWallet(user.getWallet()+para);
        userRepository.save(user);
        System.out.println("paraeklendi");
        return "redirect:/homepage";
    }

    //odeme
    @GetMapping("/checkout/user{uid}/product{pid}")
    public String checkouttest(Model model,@PathVariable("uid") Long uid, @PathVariable("pid") Long pid){
        Product product = productRepository.getById(pid);
        User user = userRepository.getById(uid);
        if(user.getWallet() < product.getPrice() && product.getStock() <= 0){
            System.out.println("urunalinmadi");
            return "redirect:/homepage";
        }
        product.setStock(product.getStock()-1);
        user.setWallet(user.getWallet()-product.getPrice());
        userRepository.save(user);
        return "redirect:/homepage";
    }


    @GetMapping("/user/all-users")
    public String listUsers(Model model) {
        List<User> users = userRepositoryService.getAll();
        model.addAttribute("users", users);
        return "back/all-users";
    }

    @GetMapping("/user/all-users/delete/{id}")
    public String userDelete(@PathVariable Long id,Model model, RedirectAttributes redirAttrs) {
        if (userRepositoryService.delete(id)){
            redirAttrs.addFlashAttribute("succes", "Users başarıyla silindi.");
        }else{
            redirAttrs.addFlashAttribute("error", "Bir Hata Oluştu!");
        }
        return "redirect:/user/all-users";
    }

}