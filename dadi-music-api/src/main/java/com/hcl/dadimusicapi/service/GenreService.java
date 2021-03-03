package com.hcl.dadimusicapi.service;

import com.hcl.dadimusicapi.exception.NotFoundException;
import com.hcl.dadimusicapi.model.Genre;
import com.hcl.dadimusicapi.repo.GenreRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class GenreService {
  @Autowired
  private GenreRepo genreRepo;

  public List<Genre> getAll() {
    log.debug("Getting all genres");
    return genreRepo.findAll();
  }

  public Genre findById(int id) {
    log.debug("Finding genre with id: " + id);
    return genreRepo.findById(id).orElseThrow(() -> new NotFoundException("Genre not found!"));
  }

  public Genre add(Genre genre) {
    log.debug("Adding genre: " + genre);
    return genreRepo.save(genre);
  }

  public Genre update(Genre genre) {
    if(!genreRepo.existsById(genre.getId())) {
      throw new NotFoundException("Cannot update genre as genre does not exist!");
    }
    log.debug("Updating genre: " + genre);
    return genreRepo.save(genre);
  }

  public void delete(int id) {
    if(!genreRepo.existsById(id)){
      log.debug("Invalid Genre Id: Genre with id # " + id + "does not exist!");
      throw new NotFoundException("Cannot delete genre as genre does not exist!");
    }
    log.debug("Deleting genre with id: " + id);
    genreRepo.deleteById(id);
  }
}
