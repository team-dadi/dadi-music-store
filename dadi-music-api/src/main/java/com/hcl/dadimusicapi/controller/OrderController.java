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

import com.hcl.dadimusicapi.model.Order;
import com.hcl.dadimusicapi.service.OrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "Get all orders", response = List.class)
	@GetMapping("/all")
	public List<Order> getAll() {
		return orderService.getAll();
	}

	@ApiOperation(value = "Get order by id", response = Order.class)
	@GetMapping("/{id}")
	public Order get(@PathVariable int id) {
		return orderService.findById(id);
	}

	@ApiOperation(value = "Add order", response = Order.class)
	@PostMapping
	public Order add(@RequestBody Order order) {
		return orderService.add(order);
	}

	@ApiOperation(value = "Delete order")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		orderService.delete(id);
		return new ResponseEntity<String>("Order deleted successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Update order", response = Order.class)
	@PutMapping
	public Order update(@RequestBody Order order) {
		return orderService.update(order);
	}

}
