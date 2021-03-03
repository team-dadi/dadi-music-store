package com.hcl.dadimusicwebapp.service;

import java.util.List;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.dadimusicwebapp.model.Genre;



@Service
@Log4j2
public class GenreService {
  @Value("${apiUrl.genre}")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<Genre> getAll() {
    String getAllUrl = url + "/all";
    log.debug("Sending get request to: " + getAllUrl);
    List<Genre> genreList = restTemplate.getForObject(getAllUrl, List.class);
    return genreList;
  }

  public Genre getById(int id) {
    String getUrl = url + "/" + id;
    log.debug("Sending get request to: " + getUrl);
    return restTemplate.getForObject(getUrl, Genre.class);
  }



  public Genre add(Genre genre) {
    log.debug("Sending post request to: " + url);
    return restTemplate.postForObject(url, genre, Genre.class);
  }

  public void update(Genre genre) {
    log.debug("Sending put request to: " + url);
    restTemplate.put(url, genre);
  }

  public void delete(int id) {
    String deleteUrl = url + "/" + id;
    log.debug("Sending delete request to: " + deleteUrl);
    restTemplate.delete(deleteUrl);
  }
}

