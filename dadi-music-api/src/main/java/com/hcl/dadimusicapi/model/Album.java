package com.hcl.dadimusicapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Album {
	@Id
	int id;
	
	String name;
	
	Double price;
	
	/*
	 * @OneToMany
	 * 
	 * @OrderColumn List <Song> song;
	 */
	
}
