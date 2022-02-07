package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.service.CustomerRetrieveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerRetrieveController {
    private final CustomerRetrieveService customerRetrieveService;

    public CustomerRetrieveController(CustomerRetrieveService customerRetrieveService) {
        this.customerRetrieveService = customerRetrieveService;
    }

    @GetMapping("/client-api/v1/customers")
    public ResponseEntity<Object> retrieveCustomerByUserNameAndId(@RequestParam(required = false) String userName, @RequestParam(required = false) String id) {
        return ResponseEntity.ok().body(customerRetrieveService.findCustomerByIdOrUsername(userName,id));
    }
}
