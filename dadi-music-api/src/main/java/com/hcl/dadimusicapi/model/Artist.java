package com.hcl.dadimusicapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
}
