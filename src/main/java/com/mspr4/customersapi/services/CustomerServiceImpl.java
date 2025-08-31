package com.mspr4.customersapi.services;

import com.mspr4.customersapi.dtos.CustomerDTO;
import com.mspr4.customersapi.entities.Customer;
import com.mspr4.customersapi.exceptions.CustomerNotFoundException;
import com.mspr4.customersapi.mappers.CustomerMapperImpl;
import com.mspr4.customersapi.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
//@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapperImpl customerMapperDto;
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
       // log.info("Saving new Customer");
        Customer customer=customerMapperDto.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapperDto.fromCustomer(savedCustomer);
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> customerMapperDto.fromCustomer(customer))
                .collect(Collectors.toList());
        return customerDTOS;
    }

    @Override
    public CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found"));
        return customerMapperDto.fromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        //log.info("Saving new Customer");
        Customer customer=customerMapperDto.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapperDto.fromCustomer(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
