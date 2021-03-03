package com.hcl.dadimusicwebapp.model;

import lombok.Data;

@Data
public class Song {

	int id;

	String name;

  Album album;

  Genre genre;

	Artist artist;

}
