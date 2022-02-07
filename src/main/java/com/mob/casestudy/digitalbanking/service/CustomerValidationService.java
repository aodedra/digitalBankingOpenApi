package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.exception.CustomerSecurityImageNotFound;
import com.mob.casestudy.digitalbanking.exception.UserNotFoundException;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;
@Component
public class CustomerValidationService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerValidationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer validateCustomer(String userName) {
       return customerRepository.findByUserName(userName).orElseThrow(()->new UserNotFoundException("The Requested User Not Found"));
    }

    public void validateCustomerSecurityImages(CustomerSecurityImages customerSecurityImages) {
        if (Objects.isNull(customerSecurityImages)) {
            throw new CustomerSecurityImageNotFound("The Requested User Security Image Not Found");
        }
    }
}
