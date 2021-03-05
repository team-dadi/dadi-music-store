
package com.hcl.dadimusicapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.dadimusicapi.model.Cart;
@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

}