package com.Softito.SoftitoECommerce.Services;

import com.Softito.SoftitoECommerce.Models.Admin;
import com.Softito.SoftitoECommerce.Repositories.AdminRepository;
import com.Softito.SoftitoECommerce.Repositories.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminRepositoryService implements IRepositoryService<Admin> {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin add(Admin entity) {
        return adminRepository.save(entity);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.gettAllAdmins();
        //return adminRepository.findAll();
    }

    @Override
    public Admin getById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        boolean status = false;
        for (Admin admin : adminRepository.findAll()){
            if(admin.getId() == id){
                admin.setIsDelete(true);
                adminRepository.save(admin);
                status = true;
                break;
            }
        }
        return status;
    }

    @Override
    public String update(Long id, Admin entity) {
        return null;
    }

}
