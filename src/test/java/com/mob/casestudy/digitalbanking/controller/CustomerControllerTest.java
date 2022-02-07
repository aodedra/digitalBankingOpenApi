package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.entities.embeddable.CustomerSecurityImagesId;
import com.mob.casestudy.digitalbanking.entities.enumrator.Language;
import com.mob.casestudy.digitalbanking.service.CustomerValidationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @InjectMocks
    CustomersController customersController;

    @Mock
    CustomerValidationService customerValidationService;

    @Test
    void retrieveUserByUserName() {
        Customer customer = Customer.builder().userName("Avada").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).build();
        String userName = "Avada odedra";
        CustomerSecurityImages abc = new CustomerSecurityImages();
        abc.setSecurityImageCaption("this is test caption");
        abc.setSecurityImages(new SecurityImages());
        CustomerSecurityImagesId abcd=new CustomerSecurityImagesId(UUID.randomUUID(),UUID.randomUUID());
        abc.setCustomerSecurityImagesId(abcd);
        customer.setCustomerSecurityImages(abc);
        CustomerSecurityImagesDto customerSecurityImagesDto = abc.toDto();
        Mockito.when(customerValidationService.validateCustomer(userName)).thenReturn(customer);
        ResponseEntity<CustomerSecurityImagesDto> body = ResponseEntity.ok().body(customerSecurityImagesDto);
        ResponseEntity<Object> objectResponseEntity = this.customersController.retrieveCustomerByUserName(userName);
        Mockito.verify(customerValidationService).validateCustomer(userName);
        Assertions.assertThat(objectResponseEntity).usingRecursiveComparison().isEqualTo(body);
        Mockito.verify(customerValidationService).validateCustomerSecurityImages(Mockito.any());
    }
}