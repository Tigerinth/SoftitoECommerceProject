package com.Softito.SoftitoECommerce.Controllers;

import com.Softito.SoftitoECommerce.Models.User;
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

    @GetMapping("/user/login")
    public String userLogin(Model model){
        return "back/login";
    }

    @PostMapping("/user/login")
    public String userLoginPost(Model model, RedirectAttributes redirAttrs,@RequestParam("email") String email, @RequestParam("password") String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        for(User user : userRepositoryService.getAll()){
            if (user.getEmail().matches(email) && passwordEncoder.matches(password, user.getPassword())) {
                return "redirect:/user/panel";
            }
        }

        redirAttrs.addFlashAttribute("error", "Böyle bir kullanıcı bulunamadı!");
        return "redirect:/user/login";
    }

    @GetMapping("/user/register")
    public String userRegister(Model model){
        return "back/register";
    }

    @PostMapping("/user/register")
    public String userRegisterPost(Model model, RedirectAttributes redirAttrs, @RequestParam("address") String address, @RequestParam("lName") String lName, @RequestParam("fName") String fName, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        if (password.equals(confirmPassword)) {
            if (fName != null && lName != null && password != null && email != null) {
                try {
                    User user = new User(email, fName, lName, encodedPassword, address);
                    userRepositoryService.add(user);
                    return "redirect:/user/panel";
                } catch (Exception ex) {
                    redirAttrs.addFlashAttribute("error", "Böyle bir user zaten var!");
                    return "redirect:/user/register";
                }
            } else {
                redirAttrs.addFlashAttribute("error", "Bir Hata Oluştu!");
                return "redirect:/user/register";
            }
        } else {
            redirAttrs.addFlashAttribute("error", "Şifreler Uyuşmadı!");
            return "redirect:/user/register";
        }
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