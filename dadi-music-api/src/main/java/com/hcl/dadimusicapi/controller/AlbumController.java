package com.hcl.dadimusicapi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dadimusicapi.model.Album;
import com.hcl.dadimusicapi.service.AlbumService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/album")
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@ApiOperation(value = "Get all albums", response = List.class)
	@GetMapping("/all")
	public List<Album> getAll() {
		return albumService.getAll();
	}
	
	@ApiOperation(value = "Find album by ID", response = Album.class )
	@GetMapping("/{id}")
	public Album get(@PathVariable int id) {
		return albumService.findById(id);
	}
	
	@ApiOperation(value= "Add an album", response = Album.class)
	@PostMapping
	public Album add(@RequestBody Album album) {
		return albumService.add(album);
	}
	
	@ApiOperation(value = "Update as album", response = Album.class)
	@PutMapping
	public Album update(@RequestBody Album album) {
		return albumService.update(album);
	}
	
	@ApiOperation(value="Delete an album by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {	
		albumService.delete(id);
		return new ResponseEntity("Task deleted successfully", HttpStatus.OK);
	}
	
	
	
	
}
