package com.hcl.dadimusicapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Artist {
	@Id
	int id;
	
	String name;
}
