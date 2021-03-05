package com.hcl.dadimusicapi.service;

import com.hcl.dadimusicapi.exception.NotFoundException;
import com.hcl.dadimusicapi.model.Cart;
import com.hcl.dadimusicapi.repo.CartRepo;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CartService {
  @Autowired
  private CartRepo cartRepo;

  public List<Cart> getAll() {
    log.debug("Getting all cart");
    return cartRepo.findAll();
  }

  public Cart findById(int id) {
    log.debug("Finding cart with id: " + id);
    return cartRepo.findById(id).orElseThrow(() -> new NotFoundException("Cart not found!"));
  }

  public Cart add(Cart cart) {
    log.debug("Adding cart: " + cart);
    return cartRepo.save(cart);
  }

  public Cart update(Cart cart) {
    if(!cartRepo.existsById(cart.getId())) {
      throw new NotFoundException("Cannot update cart as cart does not exist!");
    }
    log.debug("Updating cart: " + cart);
    return cartRepo.save(cart);
  }

  public void delete(int id) {
    if(!cartRepo.existsById(id)){
      log.debug("Invalid Cart Id: Cart with id # " + id + "does not exist!");
      throw new NotFoundException("Cannot delete cart as cart does not exist!");
    }
    log.debug("Deleting cart with id: " + id);
    cartRepo.deleteById(id);
  }
}
