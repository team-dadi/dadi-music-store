package com.hcl.dadimusicwebapp.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
   
    private int id;
    private String username;
    private String role;
    private String password;
    private String email;
    Cart cart;
    List<Invoice> invoices;
   
}