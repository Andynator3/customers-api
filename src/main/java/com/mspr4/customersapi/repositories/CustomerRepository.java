package com.mspr4.customersapi.repositories;

import com.mspr4.customersapi.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
