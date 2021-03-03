package com.hcl.dadimusicapi.service;

import com.hcl.dadimusicapi.exception.NotFoundException;
import com.hcl.dadimusicapi.model.Album;
import com.hcl.dadimusicapi.repo.AlbumRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AlbumService {
  @Autowired
  private AlbumRepo albumRepo;

  public List<Album> getAll() {
    log.debug("Get all albums");
    return albumRepo.findAll();
  }

  public Album findById(int id) {
    log.debug("Find album with id: " + id);
    return albumRepo.findById(id).orElseThrow(() -> new NotFoundException("Album not found!"));
  }

  public Album add(Album album) {
    log.debug("Add an album: " + album);
    return albumRepo.save(album);
  }

  public Album update(Album album) {
    if(!albumRepo.existsById(album.getId())) {
      throw new NotFoundException("Cannot update album as album does not exist!");
    }
    log.debug("Updating album: " + album);
    return albumRepo.save(album);
  }

  public void delete(int id) {
    if(!albumRepo.existsById(id)){
      log.debug("Invalid Album Id: Album with id # " + id + "does not exist!");
      throw new NotFoundException("Cannot delete album as album does not exist!");
    }
    log.debug("Deleting album with id: " + id);
    albumRepo.deleteById(id);
  }
}
