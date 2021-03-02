package com.hcl.dadimusicapi.enitities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Genre {
	@Id
	int id;
	
	String name;
}
