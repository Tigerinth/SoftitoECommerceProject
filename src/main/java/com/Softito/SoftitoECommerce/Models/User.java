package com.Softito.SoftitoECommerce.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(name = "f_name")
    private String fName;

    @Column(name = "l_name")
    private String lName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "is_status")
    private Boolean isStatus;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "wallet")
    private Double wallet;

    public User() {}

    public User(String email, String fName, String lName, String password, String address,Double wallet) {
        this.email = email;
        this.lName = lName;
        this.fName = fName;
        this.password = password;
        this.address = address;
        this.isStatus = true;
        this.isDelete = false;
        this.wallet = wallet;
    }
}