package com.hcl.dadimusicwebapp.model;

import java.util.List;

import lombok.Data;

@Data
public class Invoice {
	
	private int id;

	
	private List<Song> purchased;

	
	

}