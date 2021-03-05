package com.hcl.dadimusicapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.dadimusicapi.model.Invoice;

public interface OrderRepo extends JpaRepository<Invoice, Integer> {

}
