package com.Softito.SoftitoECommerce.Repositories;

import com.Softito.SoftitoECommerce.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.isDelete = false")
    public List<User> getAllUsers();
}