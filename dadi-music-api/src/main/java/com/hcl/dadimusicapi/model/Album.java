package com.hcl.dadimusicapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	Double price;
	
}
