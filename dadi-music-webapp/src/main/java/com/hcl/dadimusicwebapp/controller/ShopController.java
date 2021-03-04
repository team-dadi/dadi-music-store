package com.hcl.dadimusicwebapp.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

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
    return "index";
  }

  @GetMapping("/search")
  public String search(ModelMap model) {
    model.addAttribute("songList", songService.getAll());
    model.addAttribute("genreList", genreService.getAll());

    return "search";
  }

  @GetMapping("/search/album")
  public String searchByAlbum(@RequestParam("albumName") String albumName, ModelMap model) {
    List<Album> albumList = albumService.getAll();
    List<Song> songList = new ArrayList<Song>();
    albumList.forEach(a -> {
      if(a.getName().contains(albumName)) {
        songList.addAll(songService.getByAlbumId(a.getId()));
      }
    } );
    model.addAttribute("songList", songList);
    return "search";
  }

  @GetMapping("/search/artist")
  public String searchByArtist(@RequestParam("artistName") String artistName, ModelMap model) {
    List<Artist> artistList = artistService.getAll();
    List<Song> songList = new ArrayList<Song>();
    artistList.forEach(a -> {
      if(a.getName().contains(artistName)) {
        //songList.addAll(songService.getByArtistId(a.getId()));
      }
    } );
    model.addAttribute("songList", songList);
    return "search";
  }

  @GetMapping("/search/title")
  public String searchByTitle(@RequestParam("songName") String songName, ModelMap model) {
    List<Song> allSongs = songService.getAll();
    List<Song> songList = allSongs.stream().filter(
      s -> s.getName().contains(songName))
      .collect(Collectors.toList());
    model.addAttribute("songList", songList);
    return "search";
  }

  @GetMapping("/search/genre/{genreId}")
  public String searchByGenre(@PathVariable int genreId, ModelMap model) {
    List<Song> allSongs = songService.getAll();
    List<Song> songList = allSongs.stream().filter(
      s -> s.getGenre().getId() == genreId)
      .collect(Collectors.toList());
    model.addAttribute("songList", songList);
    return "search";
  }
}
