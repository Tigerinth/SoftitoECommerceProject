package com.Softito.SoftitoECommerce.Repositories;

import com.Softito.SoftitoECommerce.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query("SELECT a FROM Admin a WHERE a.isDelete = false")
    public List<Admin> gettAllAdmins();
}
