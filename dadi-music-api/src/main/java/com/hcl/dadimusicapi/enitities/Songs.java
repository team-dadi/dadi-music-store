package com.hcl.dadimusicapi.enitities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
@Data
public class Songs {
	
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
