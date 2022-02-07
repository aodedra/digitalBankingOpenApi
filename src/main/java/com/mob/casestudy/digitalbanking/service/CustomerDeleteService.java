package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.exception.CustomersNotFoundException;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDeleteService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDeleteService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void validateCustomer(String userName) {
        Customer customer = customerRepository.findByUserName(userName).orElseThrow(() -> new CustomersNotFoundException("The Requested Customer Not Found"));
        customerRepository.delete(customer);
    }
}
