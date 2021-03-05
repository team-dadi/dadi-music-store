
package com.hcl.dadimusicwebapp.service;

import java.util.List;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.dadimusicwebapp.model.Cart;

@Service
@Log4j2
public class CartService {
  @Value("${apiUrl.cart}")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<Cart> getAll() {
    String getAllUrl = url + "/all";
    log.debug("Sending get request to: " + getAllUrl);
    List<Cart> cartList = new ObjectMapper().convertValue(
            restTemplate.getForObject(getAllUrl, List.class),
            new TypeReference<List<Cart>>() { });
    return cartList;
  }

  public Cart getById(int id) {
    String getUrl = url + "/" + id;
    log.debug("Sending get request to: " + getUrl);
    return restTemplate.getForObject(getUrl, Cart.class);
  }

  public Cart add(Cart cart) {
    log.debug("Sending post request to: " + url);
    return restTemplate.postForObject(url, cart, Cart.class);
  }

  public void update(Cart cart) {
    log.debug("Sending put request to: " + url);
    restTemplate.put(url, cart);
  }

  public void delete(int id) {
    String deleteUrl = url + "/" + id;
    log.debug("Sending delete request to: " + deleteUrl);
    restTemplate.delete(deleteUrl);
  }
  
}