package com.hcl.dadimusicwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.dadimusicwebapp.model.Order;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderService {
	@Value("${apiUrl.order}")
	private String url;
	@Autowired
	private RestTemplate restTemplate;

	public List<Order> getAll(){
		String getAllUrl = url+"/all";
		log.debug("Sending get request to: "+ getAllUrl);
		List<Order> orderList = new ObjectMapper().convertValue(
			restTemplate.getForObject(getAllUrl, List.class),
			new TypeReference<List<Order>>() { });
		return orderList;
	}
	public Order getById(int id) {
	    String getUrl = url + "/" + id;
	    log.debug("Sending get request to: " + getUrl);
	    return restTemplate.getForObject(getUrl, Order.class);
	  }

	  public Order add(Order order) {
	    log.debug("Sending post request to: " + url);
	    return restTemplate.postForObject(url, order, Order.class);
	  }

	  public void update(Order order) {
	    log.debug("Sending put request to: " + url);
	    restTemplate.put(url, order);
	  }

	  public void delete(int id) {
	    String deleteUrl = url + "/" + id;
	    log.debug("Sending delete request to: " + deleteUrl);
	    restTemplate.delete(deleteUrl);
	  }
}