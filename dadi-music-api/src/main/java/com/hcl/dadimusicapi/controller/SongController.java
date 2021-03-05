package com.hcl.dadimusicapi.controller;

import com.hcl.dadimusicapi.model.Album;
import com.hcl.dadimusicapi.model.Artist;
import com.hcl.dadimusicapi.model.Song;
import com.hcl.dadimusicapi.service.AlbumService;
import com.hcl.dadimusicapi.service.ArtistService;
import com.hcl.dadimusicapi.service.SongService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

	@Autowired
	private SongService songService;

	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private ArtistService artistService;


	@ApiOperation(value = "Get all songs", response = List.class)
	@GetMapping("/all")
	public List<Song> getAll() {
		return songService.getAll();
	}

	@ApiOperation(value = "Get a song by id", response = Song.class)
	@GetMapping("/{id}")
	public Song get(@PathVariable int id) {
		return songService.findById(id);
	}

	@ApiOperation(value = "Get songs with album id", response = List.class)
	@GetMapping("/album/{id}")
	public List<Song> getByAlbum(@PathVariable int id) {
		Album album = albumService.findById(id);
		return songService.findByAlbum(album);
	}

	@ApiOperation(value = "Get songs with artist id", response = List.class)
	@GetMapping("/artist/{id}")
	public List<Song> getByArtist(@PathVariable int id) {
		Artist artist = artistService.findById(id);
		return songService.findByArtist(artist);
	}

	@ApiOperation(value = "Add a song", response = Song.class)
	@PostMapping
	public Song add(@RequestBody Song song) {
		return songService.add(song);
	}

	@ApiOperation(value = "Update a song", response = Song.class)
	@PutMapping
	public Song update(@RequestBody Song song) {
		return songService.update(song);
	}

	@ApiOperation(value = "Delete a song")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		songService.delete(id);
		return new ResponseEntity("Song deleted successfully", HttpStatus.OK);
	}
}
