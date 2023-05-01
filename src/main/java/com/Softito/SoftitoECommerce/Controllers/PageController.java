package com.Softito.SoftitoECommerce.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

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








}
