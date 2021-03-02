package com.hcl.dadimusicapi.enitities.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.dadimusicapi.enitities.Album;
@Repository
public interface Albumrepo extends JpaRepository<Album, Integer> {

}
