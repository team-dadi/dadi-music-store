package com.hcl.dadimusicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dadimusicapi.model.Cart;
import com.hcl.dadimusicapi.service.CartService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cart")
public class CartController {
	

	  @Autowired
	  private CartService cartService;

	  @ApiOperation(value ="Get all cart", response = List.class)
	  @GetMapping("/all")
	  public List<Cart> getAll() {
	    return cartService.getAll();
	  }

	 @ApiOperation(value ="Get cart by id", response = Cart.class)
	  @GetMapping("/{id}")
	  public Cart get(@PathVariable int id){
	    return cartService.findById(id);
	  }

	 @ApiOperation(value ="Add cart", response = Cart.class)
	  @PostMapping
	  public Cart add(@RequestBody Cart cart){
	    return cartService.add(cart);
	  }

	 @ApiOperation(value ="Delete cart by id")
	  @DeleteMapping("/{id}")
	  public ResponseEntity<String> delete(@PathVariable int id) {
	    
	   cartService.delete(id);
	    return new ResponseEntity<String>("Cart deleted successfully", HttpStatus.OK);
	  }

	 @ApiOperation(value ="Update cart", response = Cart.class)
	  @PutMapping
	  public Cart update(@RequestBody Cart cart) {
	    return cartService.update(cart);
	  }
}


