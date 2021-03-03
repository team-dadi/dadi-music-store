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

import com.hcl.dadimusicapi.model.Artist;
import com.hcl.dadimusicapi.service.ArtistService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	private ArtistService artistService;

	@ApiOperation(value = "Get all artists", response = List.class)
	@GetMapping("/all")
	public List<Artist> getAll() {
		return artistService.getAll();
	}

	@ApiOperation(value = "Get artist by id", response = Artist.class)
	@GetMapping("/{id}")
	public Artist get(@PathVariable int id) {
		return artistService.findById(id);
	}

	@ApiOperation(value = "Add artist", response = Artist.class)
	@PostMapping
	public Artist add(@RequestBody Artist artist) {
		return artistService.add(artist);
	}

	@ApiOperation(value = "Delete artist")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		artistService.delete(id);
		return new ResponseEntity<String>("Artist deleted successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Update artist", response = Artist.class)
	@PutMapping
	public Artist update(@RequestBody Artist artist) {
		return artistService.update(artist);
	}

}
