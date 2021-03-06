package com.hcl.dadimusicapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dadimusicapi.exception.NotFoundException;
import com.hcl.dadimusicapi.model.Invoice;
import com.hcl.dadimusicapi.repo.OrderRepo;

import lombok.extern.log4j.Log4j2;
@Service
@Log4j2
public class OrderService {
	 @Autowired
	  private OrderRepo orderRepo;

	  public List<Invoice> getAll() {
	    log.debug("Getting all orders");
	    return orderRepo.findAll();
	  }

	  public Invoice findById(int id) {
	    log.debug("Finding order with id: " + id);
	    return orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order not found!"));
	  }

	  public Invoice add(Invoice invoice) {
	    log.debug("Adding order: " + invoice);
	    return orderRepo.save(invoice);
	  }

	  public Invoice update(Invoice invoice) {
	    if(!orderRepo.existsById(invoice.getId())) {
	      throw new NotFoundException("Cannot update order as order does not exist!");
	    }
	    log.debug("Updating order: " + invoice);
	    return orderRepo.save(invoice);
	  }

	  public void delete(int id) {
	    if(!orderRepo.existsById(id)){
	      log.debug("Invalid Order Id: Order with id # " + id + "does not exist!");
	      throw new NotFoundException("Cannot delete order as order does not exist!");
	    }
	    log.debug("Deleting order with id: " + id);
	    orderRepo.deleteById(id);
	  }
}
