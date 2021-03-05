package com.hcl.dadimusicapi.repo;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.dadimusicapi.model.User;
@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);
}