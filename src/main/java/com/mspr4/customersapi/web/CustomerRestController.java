package com.mspr4.customersapi.web;

import com.mspr4.customersapi.dtos.CustomerDTO;
import com.mspr4.customersapi.exceptions.CustomerNotFoundException;
import com.mspr4.customersapi.services.CustomerService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerRestController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> customers(){
        return customerService.listCustomers();
    }

    @PostMapping
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomer(customerDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return customerService.findCustomerById(customerId);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(id);
        return customerService.updateCustomer( customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}
