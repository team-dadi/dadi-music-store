package com.hcl.dadimusicapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.dadimusicapi.model.Artist;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Integer> {

}
