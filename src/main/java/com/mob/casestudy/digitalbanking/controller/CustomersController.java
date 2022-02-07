package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.service.CustomerValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-service")
public class CustomersController {

    private final CustomerValidationService customerValidationService;
    public CustomersController(CustomerValidationService customerValidationService) {
        this.customerValidationService = customerValidationService;
    }

    @GetMapping("/client-api/v1/customers/{userName}")
    public ResponseEntity<Object> retrieveCustomerByUserName(@PathVariable String userName) {

        Customer customer = customerValidationService.validateCustomer(userName);
        CustomerSecurityImages customerSecurityImages = customer.getCustomerSecurityImages();
        customerValidationService.validateCustomerSecurityImages(customerSecurityImages);
        CustomerSecurityImagesDto customerSecurityImagesDto = customerSecurityImages.toDto();
        return ResponseEntity.ok().body(customerSecurityImagesDto);
    }
}
