package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.dto.CustomerDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.exception.ProvidedCustomerNotFoundException;
import com.mob.casestudy.digitalbanking.exception.UserNameAndIdNullException;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class CustomerRetrieveService {
    private final CustomerRepository customerRepository;

    public CustomerRetrieveService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto findCustomerByIdOrUsername(String userName, String id) {
        Customer customer = validateCustomerByIdAndUserName(userName, id);
        return customer.toDto();
    }

    public Customer validateCustomerByIdAndUserName(String userName, String id) {
        if (isValid(userName) && isValid(id)) {
            return customerRepository.findById(UUID.fromString(id)).orElseGet(() -> getCustomer(userName));
        }
        if (isValid(id)) {
            return customerRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ProvidedCustomerNotFoundException("Provided Customer Not Found"));
        }
        if (isValid(userName)) {
            return getCustomer(userName);
        }
        throw new UserNameAndIdNullException("Mandatory Fields Not be null or Empty");
    }

    private Customer getCustomer(String userName) {
        return customerRepository.findByUserName(userName).orElseThrow(()->new ProvidedCustomerNotFoundException("Provided Customer Not Found"));
    }
    private boolean isValid(String s){
        return Objects.nonNull(s) && !s.isEmpty();
    }
}

