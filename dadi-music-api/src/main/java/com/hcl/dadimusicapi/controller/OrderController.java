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

import com.hcl.dadimusicapi.model.Invoice;
import com.hcl.dadimusicapi.service.OrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "Get all orders", response = List.class)
	@GetMapping("/all")
	public List<Invoice> getAll() {
		return orderService.getAll();
	}

	@ApiOperation(value = "Get order by id", response = Invoice.class)
	@GetMapping("/{id}")
	public Invoice get(@PathVariable int id) {
		return orderService.findById(id);
	}

	@ApiOperation(value = "Add order", response = Invoice.class)
	@PostMapping
	public Invoice add(@RequestBody Invoice invoice) {
		return orderService.add(invoice);
	}

	@ApiOperation(value = "Delete order")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		orderService.delete(id);
		return new ResponseEntity<String>("Order deleted successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Update order", response = Invoice.class)
	@PutMapping
	public Invoice update(@RequestBody Invoice invoice) {
		return orderService.update(invoice);
	}

}
