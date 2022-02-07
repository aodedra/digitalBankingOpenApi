package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.service.CustomerDeleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerDeleteController {
    private final CustomerDeleteService customerDeleteService;

    public CustomerDeleteController(CustomerDeleteService customerDeleteService) {
        this.customerDeleteService = customerDeleteService;
    }

    @DeleteMapping("/client-api/v1/customers/{userName}")
    public ResponseEntity<Object> deleteCustomerByUserName(@PathVariable String userName) {
        customerDeleteService.validateCustomer(userName);
        return ResponseEntity.noContent().build();
    }
}
