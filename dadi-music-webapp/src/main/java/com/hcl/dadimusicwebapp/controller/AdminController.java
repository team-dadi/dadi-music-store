package com.hcl.dadimusicwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.dadimusicwebapp.model.Album;
import com.hcl.dadimusicwebapp.model.Artist;
import com.hcl.dadimusicwebapp.model.Genre;
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
	  

	  @RequestMapping("/admin")
	  public String index(ModelMap model) {
	    model.addAttribute("genreList", genreService.getAll());
	    model.addAttribute("artistList", artistService.getAll());
	    model.addAttribute("albumList", albumService.getAll());
	    model.addAttribute("songList", songService.getAll());
	    return "admin";
	  }
	  
	  @PostMapping("/add-genre")
	  public String addGenre(ModelMap model, @RequestParam("genreInput") String genreInput) {
		  model.addAttribute("genreList", genreService.getAll());
		  model.addAttribute("artistList", artistService.getAll());
		  model.addAttribute("albumList", albumService.getAll());
		  model.addAttribute("songList", songService.getAll());
		  
		  List<Genre> genreList = genreService.getAll();
		  
		  for(Genre g: genreList) {
			  if (g.getName().toLowerCase().equals(genreInput.toLowerCase())) {
				  model.put("error", "Genre already exists");
				  return "admin";
			  }
		  }
		  
		  Genre newGenre = new Genre();
		  newGenre.setId(0);
		  newGenre.setName(genreInput);
		  genreService.add(newGenre);
		  
		  return "admin";
	  }
	  
	  @RequestMapping(value="/add-album")
	  public String addAlbum(ModelMap model, @RequestParam("albumInput") String albumInput) {
		  List<Album> albumList = albumService.getAll();
		  for(Album a: albumList) {
			  if (a.getName().toLowerCase().equals(albumInput)) {
				  model.put("error", "Album already exists");
				  return "admin";
			  }
		  }
		  Album newAlbum = new Album();
		  newAlbum.setName(albumInput);
		  albumService.add(newAlbum);
		  return "admin";
	  }

	  @RequestMapping(value="/add-artist")
	  public String addArtist(ModelMap model, @RequestParam("artistInput") String artistInput) {
		  List<Artist> artistList = artistService.getAll();
		  for(Artist a: artistList) {
			  if (a.getName().toLowerCase().equals(artistInput)) {
				  model.put("error", "Artist already exists");
				  return "admin";
			  }
		  }
		  Artist newArtist = new Artist();
		  newArtist.setName(artistInput);
		  artistService.add(newArtist);
		  
		  return "admin";
	  }
	  
}
