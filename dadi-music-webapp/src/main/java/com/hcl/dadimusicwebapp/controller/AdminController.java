package com.hcl.dadimusicwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcl.dadimusicwebapp.service.AlbumService;
import com.hcl.dadimusicwebapp.service.ArtistService;
import com.hcl.dadimusicwebapp.service.GenreService;
import com.hcl.dadimusicwebapp.service.SongService;

@Controller
public class AdminController {

	  @Autowired
	  private SongService songService;

	  @Autowired
	  private GenreService genreService;

	  @Autowired
	  private AlbumService albumService;

	  @Autowired
	  private ArtistService artistService;
	  

	  @GetMapping("/admin")
	  public String index(ModelMap model) {
	    model.addAttribute("genreList", genreService.getAll());
	    model.addAttribute("artistList", artistService.getAll());
	    model.addAttribute("albumList", albumService.getAll());
	    return "admin";
	  }
	  
	  
}
