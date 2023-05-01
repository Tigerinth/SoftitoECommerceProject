package com.Softito.SoftitoECommerce.Controllers;

import com.Softito.SoftitoECommerce.Models.Admin;
import com.Softito.SoftitoECommerce.Repositories.AdminRepository;
import com.Softito.SoftitoECommerce.Services.AdminRepositoryService;
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
public class AdminController {
    @Autowired
    private AdminRepositoryService adminRepositoryService;

    @GetMapping("/admin/login")
    public String adminLogin(Model model){
        return "back/login";
    }

    @PostMapping("/admin/login")
    public String adminLoginPost(Model model, RedirectAttributes redirAttrs,@RequestParam("email") String email, @RequestParam("password") String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        for(Admin admin : adminRepositoryService.getAll()){
            if (admin.getEmail().matches(email) && passwordEncoder.matches(password, admin.getPassword())) {
                return "redirect:/admin/panel";
            }
        }

        redirAttrs.addFlashAttribute("error", "Böyle bir kullanıcı bulunamadı!");
        return "redirect:/admin/login";
    }

    @GetMapping("/admin/register")
    public String adminRegister(Model model){
        return "back/register";
    }

    @PostMapping("/admin/register")
    public String adminRegisterPost(Model model, RedirectAttributes redirAttrs, @RequestParam("lName") String lName, @RequestParam("fName") String fName, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        if (password.equals(confirmPassword)) {
            if (fName != null && lName != null && password != null && email != null) {
                try {
                    Admin admin = new Admin(email, fName, lName, encodedPassword);
                    adminRepositoryService.add(admin);
                    return "redirect:/admin/panel";
                } catch (Exception ex) {
                    redirAttrs.addFlashAttribute("error", "Böyle bir admin zaten var!");
                    return "redirect:/admin/register";
                }
            } else {
                redirAttrs.addFlashAttribute("error", "Bir Hata Oluştu!");
                return "redirect:/admin/register";
            }
        } else {
            redirAttrs.addFlashAttribute("error", "Şifreler Uyuşmadı!");
            return "redirect:/admin/register";
        }
    }
    @GetMapping("/admin/all-admins")
    public String listAdmins(Model model) {
        List<Admin> admins = adminRepositoryService.getAll();
        model.addAttribute("admins", admins);
        return "back/all-admins";
    }

    @GetMapping("/admin/all-admins/delete/{id}")
    public String adminDelete(@PathVariable Long id,Model model, RedirectAttributes redirAttrs) {
        if (adminRepositoryService.delete(id)){
            redirAttrs.addFlashAttribute("succes", "Admin başarıyla silindi.");
        }else{
            redirAttrs.addFlashAttribute("error", "Bir Hata Oluştu!");
        }
        return "redirect:/admin/all-admins";
    }




}
