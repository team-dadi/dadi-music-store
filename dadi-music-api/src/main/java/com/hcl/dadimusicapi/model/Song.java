package com.hcl.dadimusicapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
@Data
public class Song {
	
	@Id
	int id;
	String name;
	@ManyToOne
	Album album;
	@ManyToOne
	Genre genre;
	@ManyToOne
	Artist artist;
	

}
