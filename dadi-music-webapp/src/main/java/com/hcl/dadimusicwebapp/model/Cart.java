package com.hcl.dadimusicwebapp.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
public class Cart {

	int id;

	List<Song> songs;

}
