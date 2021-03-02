package com.hcl.dadimusicapi.enitities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import lombok.Data;
@Data
@Entity
public class Album {
	@Id
	int id;
	
	String name;
	
	Double price;
	
	@OneToMany
	@OrderColumn
	List <Songs> song;

	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
