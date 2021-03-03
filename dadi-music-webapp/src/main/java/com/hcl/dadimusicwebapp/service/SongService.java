package com.hcl.dadimusicwebapp.service;

import com.hcl.dadimusicwebapp.model.Song;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Log4j2
public class SongService {
  @Value("${apiUrl.song}")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<Song> getAll() {
    String getAllUrl = url + "/all";
    log.debug("Sending get request to: " + getAllUrl);
    List<Song> songList = restTemplate.getForObject(getAllUrl, List.class);
    return songList;
  }

  public Song getById(int id) {
    String getUrl = url + "/" + id;
    log.debug("Sending get request to: " + getUrl);
    return restTemplate.getForObject(getUrl, Song.class);
  }

  public List<Song> getByAlbumId(int id) {
    String getByAlbumUrl = url + "/album/" + id;
    log.debug("Sending get request to: " + getByAlbumUrl);
    List<Song> songList = restTemplate.getForObject(getByAlbumUrl, List.class);
    return songList;
  }

  public Song add(Song song) {
    log.debug("Sending post request to: " + url);
    return restTemplate.postForObject(url, song, Song.class);
  }

  public void update(Song song) {
    log.debug("Sending put request to: " + url);
    restTemplate.put(url, song);
  }

  public void delete(int id) {
    String deleteUrl = url + "/" + id;
    log.debug("Sending delete request to: " + deleteUrl);
    restTemplate.delete(deleteUrl);
  }
}
