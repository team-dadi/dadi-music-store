package com.hcl.dadimusicapi.service;

import com.hcl.dadimusicapi.exception.NotFoundException;
import com.hcl.dadimusicapi.model.Album;
import com.hcl.dadimusicapi.model.Artist;
import com.hcl.dadimusicapi.model.Song;
import com.hcl.dadimusicapi.repo.SongRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SongService {
	@Autowired
	private SongRepo songRepo;

	public List<Song> getAll() {
		log.debug("Getting all songs");
		return songRepo.findAll();
	}

	public List<Song> findByAlbum(Album album) {
		log.debug("Finding songs with album: " + album);
		return songRepo.findAllByAlbum(album);
	}

	public List<Song> findByArtist(Artist artist) {
		log.debug("Finding songs with artist: " + artist);
		return songRepo.findAllByArtist(artist);
	}

	public Song findById(int id) {
		log.debug("Finding song with id: " + id);
		return songRepo.findById(id).orElseThrow(() -> new NotFoundException("Song not found!"));
	}

	public Song add(Song song) {
		log.debug("Adding song: " + song);
		return songRepo.save(song);
	}

	public Song update(Song song) {
		if (!songRepo.existsById(song.getId())) {
			throw new NotFoundException("Cannot update song as song does not exist!");
		}
		log.debug("Updating song: " + song);
		return songRepo.save(song);
	}

	public void delete(int id) {
		if (!songRepo.existsById(id)) {
			log.debug("Invalid Song Id: Song with id # " + id + "does not exist!");
			throw new NotFoundException("Cannot delete song as song does not exist!");
		}
		log.debug("Deleting song with id: " + id);
		songRepo.deleteById(id);
	}
}
