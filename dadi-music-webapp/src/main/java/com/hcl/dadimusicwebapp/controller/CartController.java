package com.hcl.dadimusicwebapp.controller;

import com.hcl.dadimusicwebapp.model.Cart;
import com.hcl.dadimusicwebapp.model.Song;
import com.hcl.dadimusicwebapp.service.CartService;
import com.hcl.dadimusicwebapp.service.SongService;
import com.hcl.dadimusicwebapp.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
public class CartController {
  @Autowired
  private CartService cartService;

  @Autowired
  private SongService songService;

  @Autowired
  private UserService userService;

  @GetMapping("/cart")
  public String cart(ModelMap model, HttpServletRequest httpServletRequest) {
    String username = httpServletRequest.getRemoteUser();

    if(username == null) {
      Cart guestCart = (Cart) httpServletRequest.getSession().getAttribute("cart");
      model.addAttribute("cart", guestCart);
    } else {
      Cart userCart = cartService.getById(userService.findByUsername(username).getCart().getId());
      model.addAttribute("cart", userCart);
    }

    return "cart";
  }

  @GetMapping("/add/{id}")
  public void addSong(@PathVariable int id, HttpServletRequest httpServletRequest) {
    String username = httpServletRequest.getRemoteUser();
    Cart guestCart = (Cart) httpServletRequest.getSession().getAttribute("cart");
    Song song = songService.getById(id);

    if (guestCart == null && username == null) {        // not logged in, no guest cart (new)
      Cart newCart = new Cart();
      newCart.setSongs(new ArrayList<Song>());
      newCart.getSongs().add(song);

      httpServletRequest.getSession(true)
        .setAttribute("cart", newCart );
    } else if(guestCart != null && username != null) {   // is logged in, has guest cart (transfer, remove guest cart)
      Cart userCart = cartService.getById(userService.findByUsername(username).getCart().getId());

      if (userCart.getSongs() == null) {
        userCart.setSongs(new ArrayList<Song>());
      }
      userCart.getSongs().addAll(guestCart.getSongs());
      userCart.getSongs().add(song);
      cartService.update(userCart);

      httpServletRequest.removeAttribute("cart");
    } else if(username != null) {                     // user logged in, no guest cart
      Cart userCart = cartService.getById(userService.findByUsername(username).getCart().getId());
      if (userCart.getSongs() == null) {
        userCart.setSongs(new ArrayList<Song>());
      }
      userCart.getSongs().add(song);
      cartService.update(userCart);
    } else {                                        // not logged in, has guest cart (add)
      guestCart.getSongs().add(song);
      httpServletRequest.getSession()
        .setAttribute("cart", guestCart );
    }
  }


}
