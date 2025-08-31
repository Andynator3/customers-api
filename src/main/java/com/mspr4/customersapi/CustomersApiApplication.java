package com.mspr4.customersapi;

import com.mspr4.customersapi.dtos.CustomerDTO;
import com.mspr4.customersapi.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class CustomersApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomersApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerService customerService) {
        return args -> {
            // Create a customer list
            Stream.of("Pascal", "Aline", "Pierre").forEach(name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerService.saveCustomer(customer);
            });
        };
    }
}