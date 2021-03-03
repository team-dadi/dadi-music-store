package com.hcl.dadimusicapi.service;

import com.hcl.dadimusicapi.exception.NotFoundException;
import com.hcl.dadimusicapi.model.Artist;
import com.hcl.dadimusicapi.repo.ArtistRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ArtistService {
  @Autowired
  private ArtistRepo artistRepo;

  public List<Artist> getAll() {
    log.debug("Getting all artists");
    return artistRepo.findAll();
  }

  public Artist findById(int id) {
    log.debug("Finding artist with id: " + id);
    return artistRepo.findById(id).orElseThrow(() -> new NotFoundException("Artist not found!"));
  }

  public Artist add(Artist artist) {
    log.debug("Adding artist: " + artist);
    return artistRepo.save(artist);
  }

  public Artist update(Artist artist) {
    if(!artistRepo.existsById(artist.getId())) {
      throw new NotFoundException("Cannot update artist as artist does not exist!");
    }
    log.debug("Updating artist: " + artist);
    return artistRepo.save(artist);
  }

  public void delete(int id) {
    if(!artistRepo.existsById(id)){
      log.debug("Invalid Artist Id: Artist with id # " + id + "does not exist!");
      throw new NotFoundException("Cannot delete artist as artist does not exist!");
    }
    log.debug("Deleting artist with id: " + id);
    artistRepo.deleteById(id);
  }
}
