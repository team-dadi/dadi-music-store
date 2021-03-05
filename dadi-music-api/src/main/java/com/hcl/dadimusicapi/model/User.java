package com.hcl.dadimusicapi.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String role;
	private String password;
	private String email;
	@OneToOne
	Cart cart;

	@OneToMany
	List<Order> orders;

}