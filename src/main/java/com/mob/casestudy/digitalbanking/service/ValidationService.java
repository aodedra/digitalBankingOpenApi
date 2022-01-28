package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.exception.CustomerSecurityImageNotFound;
import com.mob.casestudy.digitalbanking.exception.UserNotFoundException;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.springframework.stereotype.Component;


@Component
public class ValidationService {
    private final CustomerRepository customerRepository;

    public ValidationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer validateUser(String userName) {

        Customer customer = customerRepository.findByUserName(userName);

        if (!customerRepository.existsByUserName(userName)) {
            throw new UserNotFoundException("The Requested User Not Found");
        }
        return customer;
    }

    public void validateImages(CustomerSecurityImages customerSecurityImages) {

        if (customerSecurityImages==null) {
            throw new CustomerSecurityImageNotFound("The Requested User Security Image Not Found");
        }


    }
}
