package com.Softito.SoftitoECommerce.Services;

import com.Softito.SoftitoECommerce.Models.User;
import com.Softito.SoftitoECommerce.Repositories.IRepositoryService;
import com.Softito.SoftitoECommerce.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRepositoryService implements IRepositoryService<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAllUsers();
        //return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        boolean status = false;
        for (User user : userRepository.findAll()){
            if(user.getId() == id){
                user.setIsDelete(true);
                userRepository.save(user);
                status = true;
                break;
            }
        }
        return status;
    }

    @Override
    public String update(Long id, User entity) {
        return null;
    }

}