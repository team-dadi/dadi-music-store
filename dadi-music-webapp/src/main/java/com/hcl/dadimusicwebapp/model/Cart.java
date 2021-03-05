package com.hcl.dadimusicwebapp.model;

import java.util.List;

import lombok.Data;

@Data
public class Cart {

	int id;

	List<Song> songs;

}
