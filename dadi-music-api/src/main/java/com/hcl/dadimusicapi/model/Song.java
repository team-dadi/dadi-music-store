package com.hcl.dadimusicapi.model;

import javax.persistence.*;

import lombok.Data;
@Entity
@Data
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	@ManyToOne
	Album album;
	@ManyToOne
	Genre genre;
	@ManyToOne
	Artist artist;
	

}
