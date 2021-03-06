package com.hcl.dadimusicwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.dadimusicwebapp.model.Invoice;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderService {
	@Value("${apiUrl.order}")
	private String url;
	@Autowired
	private RestTemplate restTemplate;

	public List<Invoice> getAll(){
		String getAllUrl = url+"/all";
		log.debug("Sending get request to: "+ getAllUrl);
		List<Invoice> invoiceList = new ObjectMapper().convertValue(
			restTemplate.getForObject(getAllUrl, List.class),
			new TypeReference<List<Invoice>>() { });
		return invoiceList;
	}
	public Invoice getById(int id) {
	    String getUrl = url + "/" + id;
	    log.debug("Sending get request to: " + getUrl);
	    return restTemplate.getForObject(getUrl, Invoice.class);
	  }

	  public Invoice add(Invoice invoice) {
	    log.debug("Sending post request to: " + url);
	    return restTemplate.postForObject(url, invoice, Invoice.class);
	  }

	  public void update(Invoice invoice) {
	    log.debug("Sending put request to: " + url);
	    restTemplate.put(url, invoice);
	  }

	  public void delete(int id) {
	    String deleteUrl = url + "/" + id;
	    log.debug("Sending delete request to: " + deleteUrl);
	    restTemplate.delete(deleteUrl);
	  }
}