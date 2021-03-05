package com.hcl.dadimusicwebapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.dadimusicwebapp.model.Cart;
import com.hcl.dadimusicwebapp.model.User;

@Service
public class UserService {

	private static final Logger log = LogManager.getLogger(UserService.class);

	@Value("${apiUrl.user.findByUsername}")
	private String findUrl;
	@Value("${apiUrl.user.add}")
	private String addUrl;
	@Value("${apiUrl.user.usernameTaken}")
	private String usernameTakenUrl;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncorder;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CartService cartService;

	public User findByUsername(String username) {
		String url = findUrl + username;
		log.debug("Sending get request to " + url + " to find user with username: " + username);

		User user = restTemplate.getForObject(url, User.class);
		return user;
	}

	public User addUser(User account) {
		User user = new User();
		Cart cart = cartService.add(new Cart());
		
		user.setUsername(account.getUsername());
		user.setPassword(bCryptPasswordEncorder.encode(account.getPassword()));
		user.setEmail(account.getEmail());
		user.setRole("ROLE_USER");
		user.setCart(cart);

		log.debug("Sending post request to " + addUrl + " to add user: " + user);

		return restTemplate.postForObject(addUrl, user, User.class);
	}

	public Boolean usernameExists(String username) {
		log.debug(
				"Sending get request to " + usernameTakenUrl + username + " to check if username exists: " + username);
		return restTemplate.getForObject(usernameTakenUrl + username, Boolean.class);
	}
}