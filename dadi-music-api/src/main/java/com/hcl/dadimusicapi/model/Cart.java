package com.hcl.dadimusicapi.model;

import java.util.List;

import javax.persistence.*;


import lombok.Data;

@Entity
@Data
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@OneToMany
	List<Song> songs;

}
