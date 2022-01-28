package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.service.ValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/customer-service")
public class CustomersController {

    private final ValidationService validationService;

    public CustomersController(ValidationService validationService) {

        this.validationService = validationService;
    }

    @GetMapping("/client-api/v1/customers/{userName}")
    public ResponseEntity<Object> retrieveCustomerByUserName(@PathVariable String userName) {

        Customer customer = validationService.validateUser(userName);

        CustomerSecurityImages customerSecurityImages=customer.getCustomerSecurityImages();
        validationService.validateImages(customerSecurityImages);

        CustomerSecurityImagesDto customerSecurityImagesDto=customerSecurityImages.toDto();

        return ResponseEntity.ok().body(customerSecurityImagesDto);

    }



}
