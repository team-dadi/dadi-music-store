package com.hcl.dadimusicwebapp.controller;

import com.hcl.dadimusicwebapp.model.Cart;
import com.hcl.dadimusicwebapp.model.Invoice;
import com.hcl.dadimusicwebapp.model.Song;
import com.hcl.dadimusicwebapp.model.User;
import com.hcl.dadimusicwebapp.service.CartService;
import com.hcl.dadimusicwebapp.service.OrderService;
import com.hcl.dadimusicwebapp.service.SongService;
import com.hcl.dadimusicwebapp.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@Autowired
	private OrderService orderService;

	@GetMapping("/cart")
	public String cart(ModelMap model, HttpServletRequest httpServletRequest) {
		String username = httpServletRequest.getRemoteUser();
		System.out.println("here---------->" + username);
		log.debug(username);

		if (username == null) {
			Cart guestCart = (Cart) httpServletRequest.getSession().getAttribute("cart");
			if (guestCart == null) {
				guestCart = new Cart();
				guestCart.setSongs(new ArrayList<Song>());

				httpServletRequest.getSession(true).setAttribute("cart", guestCart);
			}
			model.addAttribute("songList", guestCart.getSongs());
		} else {

			Cart userCart = cartService.getById(userService.findByUsername(username).getCart().getId());
			model.addAttribute("songList", userCart.getSongs());
		}

		return "cart";
	}

	@GetMapping("/add/{id}")
	public ResponseEntity addSong(@PathVariable int id, HttpServletRequest httpServletRequest) {
		String username = httpServletRequest.getRemoteUser();
		Cart guestCart = (Cart) httpServletRequest.getSession().getAttribute("cart");
		Song song = songService.getById(id);

		if (song == null) {
			return new ResponseEntity<String>("Couldn't find song with :" + id, HttpStatus.NOT_FOUND);
		}

		log.debug("Cart is adding song: " + song);

		if (guestCart == null && username == null) { // not logged in, no guest cart (new)
			Cart newCart = new Cart();
			newCart.setSongs(new ArrayList<Song>());
			newCart.getSongs().add(song);

			httpServletRequest.getSession(true).setAttribute("cart", newCart);
		} else if (guestCart != null && username != null) { // is logged in, has guest cart (transfer, remove guest
															// cart)
			Cart userCart = cartService.getById(userService.findByUsername(username).getCart().getId());

			if (userCart.getSongs() == null) {
				userCart.setSongs(new ArrayList<Song>());
			}
			userCart.getSongs().addAll(guestCart.getSongs());
			userCart.getSongs().add(song);
			cartService.update(userCart);

			httpServletRequest.removeAttribute("cart");
		} else if (username != null) { // user logged in, no guest cart
			Cart userCart = cartService.getById(userService.findByUsername(username).getCart().getId());
			if (userCart.getSongs() == null) {
				userCart.setSongs(new ArrayList<Song>());
			}
			userCart.getSongs().add(song);
			cartService.update(userCart);
		} else { // not logged in, has guest cart (add)
			guestCart.getSongs().add(song);
			httpServletRequest.getSession().setAttribute("cart", guestCart);
		}
		return new ResponseEntity<String>("Song added to cart successfully", HttpStatus.OK);
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity deleteSong(@PathVariable int id, HttpServletRequest httpServletRequest) {
		String username = httpServletRequest.getRemoteUser();
		Cart guestCart = (Cart) httpServletRequest.getSession().getAttribute("cart");
		Song song = songService.getById(id);

		if (song == null) {
			return new ResponseEntity<String>("Couldn't find song with :" + id, HttpStatus.NOT_FOUND);
		}

		log.debug("Cart is removing song: " + song);

		if (guestCart == null && username == null) { // not logged in, no guest cart (error)
			return new ResponseEntity<String>("Nothing in cart to delete" + id, HttpStatus.NOT_FOUND);
		} else if (guestCart != null && username != null) { // is logged in, has guest cart (transfer, remove guest
															// cart)
			Cart userCart = cartService.getById(userService.findByUsername(username).getCart().getId());

			if (userCart.getSongs() == null) {
				userCart.setSongs(new ArrayList<Song>());
			}
			userCart.getSongs().addAll(guestCart.getSongs());
			if (userCart.getSongs().contains(song)) {
				userCart.getSongs().remove(song);
			} else {
				return new ResponseEntity<String>("Cannot delete song that is not in cart" + id, HttpStatus.NOT_FOUND);
			}

			cartService.update(userCart);

			httpServletRequest.removeAttribute("cart");
		} else if (username != null) { // user logged in, no guest cart
			Cart userCart = cartService.getById(userService.findByUsername(username).getCart().getId());
			if (userCart.getSongs() == null) {
				return new ResponseEntity<String>("Nothing in cart to delete" + id, HttpStatus.NOT_FOUND);
			}

			if (userCart.getSongs().contains(song)) {
				userCart.getSongs().remove(song);
				cartService.update(userCart);
			} else {
				return new ResponseEntity<String>("Cannot delete song that is not in cart" + id, HttpStatus.NOT_FOUND);
			}

		} else { // not logged in, has guest cart (add)
			if (guestCart.getSongs().contains(song)) {
				guestCart.getSongs().remove(song);
				httpServletRequest.getSession().setAttribute("cart", guestCart);
			} else {
				return new ResponseEntity<String>("Cannot delete song that is not in cart" + id, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<String>("Song delete from cart successfully", HttpStatus.OK);
	}

	@GetMapping("/checkout")
	public String checkout(ModelMap model, HttpServletRequest httpServletRequest, RedirectAttributes attributes) {
		String username = httpServletRequest.getRemoteUser();

		if (username == null) {
			Cart guestCart = (Cart) httpServletRequest.getSession().getAttribute("cart");
			if (guestCart == null) {
				guestCart = new Cart();
				guestCart.setSongs(new ArrayList<Song>());

				httpServletRequest.getSession(true).setAttribute("cart", guestCart);
				attributes.addFlashAttribute("cartAlert", "Nothing in Cart to Checkout!");
				return "redirect:/cart";
			} else if (guestCart.getSongs().isEmpty() || guestCart.getSongs() == null) {
				attributes.addFlashAttribute("cartAlert", "Nothing in Cart to Checkout!");
				return "redirect:/cart";
			}
			model.addAttribute("songList", guestCart.getSongs());
		} else {
			Cart userCart = cartService.getById(userService.findByUsername(username).getCart().getId());
			model.addAttribute("songList", userCart.getSongs());
		}

		return "checkout";
	}

	@PostMapping("/checkout")
	public String confirmOrder(ModelMap model, HttpServletRequest httpServletRequest, RedirectAttributes attributes) {
		String username = httpServletRequest.getRemoteUser();

		if (username == null) {
			Cart guestCart = (Cart) httpServletRequest.getSession().getAttribute("cart");
			if (guestCart == null) {
				guestCart = new Cart();
				guestCart.setSongs(new ArrayList<Song>());

				httpServletRequest.getSession(true).setAttribute("cart", guestCart);
				attributes.addFlashAttribute("cartAlert", "Nothing in Cart to Checkout!");
				return "redirect:/cart";
			} else if (guestCart.getSongs().isEmpty() || guestCart.getSongs() == null) {
				attributes.addFlashAttribute("cartAlert", "Nothing in Cart to Checkout!");
				return "redirect:/cart";
			}

			List<Song> purchase = guestCart.getSongs();
			httpServletRequest.getSession().removeAttribute("cart");

			Invoice guestInvoice = new Invoice();
			guestInvoice.setPurchased(purchase);

			Invoice addedInvoice = orderService.add(guestInvoice);
			model.addAttribute("invoice", addedInvoice);
		} else {
			List<Song> userPurchases = cartService.getById(userService.findByUsername(username).getCart().getId())
					.getSongs();

			Invoice guestInvoice = new Invoice();
			guestInvoice.setPurchased(userPurchases);

			Invoice addedInvoice = orderService.add(guestInvoice);
			model.addAttribute("invoice", addedInvoice);

			int id = userService.findByUsername(username).getCart().getId();
			Invoice invoice= new Invoice();
			invoice.setId(addedInvoice.getId());
			User user = userService.findByUsername(username);
			user.getInvoices().add(invoice);
			userService.addUser(user);
			Cart cart=new Cart();
			cart.setId(id);
			cart.setSongs(new ArrayList <Song>());
			
			cartService.update(cart);
		}

		return "invoice";
	}
}
