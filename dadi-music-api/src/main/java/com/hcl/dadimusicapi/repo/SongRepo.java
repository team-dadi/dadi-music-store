package com.hcl.dadimusicapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.dadimusicapi.model.Album;
import com.hcl.dadimusicapi.model.Artist;
import com.hcl.dadimusicapi.model.Song;
@Repository
public interface SongRepo extends JpaRepository<Song, Integer> {
	List <Song> findAllByAlbum(Album album);
	List <Song> findAllByArtist (Artist artist);
}
