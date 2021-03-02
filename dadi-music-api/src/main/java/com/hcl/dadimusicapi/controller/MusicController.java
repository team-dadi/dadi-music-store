package com.hcl.dadimusicapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dadimusicapi.enitities.Album;
import com.hcl.dadimusicapi.enitities.Artist;
import com.hcl.dadimusicapi.enitities.Genre;
import com.hcl.dadimusicapi.enitities.Songs;
import com.hcl.dadimusicapi.enitities.repo.Albumrepo;
import com.hcl.dadimusicapi.enitities.repo.Artistrepo;
import com.hcl.dadimusicapi.enitities.repo.Genrerepo;
import com.hcl.dadimusicapi.enitities.repo.Songsrepo;

@RestController
public class MusicController {

	Songs song = new Songs();
	Album album = new Album();
	Artist artist = new Artist();
	Genre genre = new Genre();
	List<Songs> albumSongs = new ArrayList<>();
	@Autowired
	Songsrepo repo;
	@Autowired
	Albumrepo albumrepo;
	@Autowired
	Artistrepo artistrepo;
	@Autowired
	Genrerepo genrerepo;

	@GetMapping(path = "/")
	public String Initi() {

		song.setId(1);
		song.setName("my first song");

		album.setId(1);
		album.setName("sample album");
		album.setPrice(1.00);

		artist.setId(1);
		artist.setName("sample artist");
		song.setArtist(artist);

		genre.setId(1);
		genre.setName("sample genre");
		song.setGenre(genre);

		/*
		 * albumSongs.add(song); album.setSong(albumSongs); song.setAlbum(album);
		 */

		System.out.println(song.toString());

		albumrepo.save(album);
		artistrepo.save(artist);
		genrerepo.save(genre);

		repo.save(song);

		return repo.findById(1).get().toString();

	}
}
