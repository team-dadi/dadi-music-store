package com.hcl.dadimusicwebapp.model;

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
   
}