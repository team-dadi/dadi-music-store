package com.hcl.dadimusicapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.dadimusicapi.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
