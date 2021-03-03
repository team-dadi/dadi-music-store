
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

import com.hcl.dadimusicapi.model.Genre;
import com.hcl.dadimusicapi.service.GenreService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/genre")
public class GenreController {
	

	  @Autowired
	  private GenreService genreService;

	  @ApiOperation(value ="Get all genres", response = List.class)
	  @GetMapping("/all")
	  public List<Genre> getAll() {
	    return genreService.getAll();
	  }

	 @ApiOperation(value ="Get genre by id", response = Genre.class)
	  @GetMapping("/{id}")
	  public Genre get(@PathVariable int id){
	    return genreService.findById(id);
	  }

	 @ApiOperation(value ="Add genre by id", response = Genre.class)
	  @PostMapping
	  public Genre add(@RequestBody Genre genre){
	    return genreService.add(genre);
	  }

	 @ApiOperation(value ="Delete genre by id")
	  @DeleteMapping("/{id}")
	  public ResponseEntity<String> delete(@PathVariable int id) {
	    
	   genreService.delete(id);
	    return new ResponseEntity<String>("Genre deleted successfully", HttpStatus.OK);
	  }

	 @ApiOperation(value ="Update genre by id", response = Genre.class)
	  @PutMapping
	  public Genre update(@RequestBody Genre genre) {
	    return genreService.update(genre);
	  }


}