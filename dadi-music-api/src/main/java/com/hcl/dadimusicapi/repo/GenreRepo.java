package com.hcl.dadimusicapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.dadimusicapi.model.Genre;
@Repository
public interface GenreRepo extends JpaRepository<Genre, Integer> {

}
