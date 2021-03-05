package com.hcl.dadimusicwebapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.hcl.dadimusicwebapp.model.Album;
import com.hcl.dadimusicwebapp.model.Artist;
import com.hcl.dadimusicwebapp.model.Song;
import com.hcl.dadimusicwebapp.service.AlbumService;
import com.hcl.dadimusicwebapp.service.ArtistService;
import com.hcl.dadimusicwebapp.service.GenreService;
import com.hcl.dadimusicwebapp.service.SongService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
public class ShopController {
  @Autowired
  private SongService songService;

  @Autowired
  private GenreService genreService;

  @Autowired
  private AlbumService albumService;

  @Autowired
  private ArtistService artistService;

  @GetMapping("/")
  public String index(ModelMap model) {
    model.addAttribute("genreList", genreService.getAll());
    return "home";
  }

  @GetMapping("/search")
  public String searchByAlbum(@RequestParam(required = false) String albumName,
                              @RequestParam(required = false) String artistName,
                              @RequestParam(required = false) String songName,
                              @RequestParam(required = false) String genreId,
                              ModelMap model) {
    List<Song> songList = new ArrayList<>();

    if(albumName != null) {
      songList.addAll(searchByAlbum(albumName));
    } else if(songName != null) {
      songList.addAll(searchByTitle(songName));
    } else if(artistName != null) {
      songList.addAll(searchByArtist(songName));
    } else if(genreId != null) {
      songList.addAll(searchByGenre(Integer.parseInt(genreId)));
    }
    else {
      songList.addAll(songService.getAll());
    }

    model.addAttribute("songList", songList);
    model.addAttribute("genreList", genreService.getAll());
    return "search";
  }


  private List<Song> searchByAlbum(String albumName) {
    log.debug("Searching in albums for: " + albumName);

    List<Album> albumList = albumService.getAll();
    List<Song> songList = new ArrayList<Song>();

    albumList.forEach(a -> {
      if(a.getName().contains(albumName)) {
        songList.addAll(songService.getByAlbumId(a.getId()));
      }
    } );

    return songList;
  }

  private List<Song> searchByArtist(String artistName) {
    List<Artist> artistList = artistService.getAll();
    List<Song> songList = new ArrayList<Song>();
    artistList.forEach(a -> {
      if(a.getName().contains(artistName)) {
        songList.addAll(songService.getByArtistId(a.getId()));
      }
    } );

    return songList;
  }

  private List<Song> searchByTitle(String songName) {
    List<Song> allSongs = songService.getAll();
    List<Song> songList = allSongs.stream().filter(
      s -> s.getName().contains(songName))
      .collect(Collectors.toList());

    return songList;
  }

  public List<Song> searchByGenre(int genreId) {
    List<Song> allSongs = songService.getAll();
    List<Song> songList = allSongs.stream().filter(
      s -> s.getGenre().getId() == genreId)
      .collect(Collectors.toList());

    return songList;
  }
}
