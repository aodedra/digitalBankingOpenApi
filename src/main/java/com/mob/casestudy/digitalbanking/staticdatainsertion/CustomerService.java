package com.mob.casestudy.digitalbanking.staticdatainsertion;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import com.mob.casestudy.digitalbanking.entities.embeddable.CustomerSecurityImagesId;
import com.mob.casestudy.digitalbanking.entities.enumrator.Language;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import com.mob.casestudy.digitalbanking.repository.CustomerSecurityImagesRepository;
import com.mob.casestudy.digitalbanking.repository.SecurityImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SecurityImagesRepository securityImagesRepository;
    @Autowired
    CustomerSecurityImagesRepository customerSecurityImagesRepository;

    public void addCustomer() {
        Customer customer = Customer.builder().userName("Avada").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).build();
        customerRepository.save(customer);
        Customer customerOne = Customer.builder().userName("raj").firstName("kanani").lastName("patel").phoneNumber("1234567890").email("raj@mobbiquityinc.com").preferredLanguages(Language.FR).build();
        customerRepository.save(customerOne);
        addImages(customer);
    }

    public void addImages(Customer customer) {
        List<SecurityImages> list = new ArrayList<>();
        SecurityImages securityImage = SecurityImages.builder().securityImageName("Car").securityImageUrl("www.carsworld.com").build();
        securityImagesRepository.save(securityImage);
        SecurityImages securityImageOne = SecurityImages.builder().securityImageName("Bike").securityImageUrl("www.bikesworld.com").build();
        securityImagesRepository.save(securityImageOne);
        list.add(securityImage);
        setImagesForCustomer(customer, list);
    }

    public void setImagesForCustomer(Customer customer, List<SecurityImages> securityImages) {
        List<CustomerSecurityImages> list = new ArrayList<>();
        CustomerSecurityImages customerSecurityImages = CustomerSecurityImages.builder().customerSecurityImagesId(new CustomerSecurityImagesId()).securityImageCaption("BMW").createdOn(LocalDateTime.now()).build();
        CustomerSecurityImages customerSecurityImagesOne = CustomerSecurityImages.builder().customerSecurityImagesId(new CustomerSecurityImagesId()).securityImageCaption("Mystic").createdOn(LocalDateTime.now()).build();
        list.add(customerSecurityImages);
        list.add(customerSecurityImagesOne);
        customerSecurityImages.setCustomer(customer);
        customerSecurityImages.setSecurityImages(securityImages.get(0));
        customerSecurityImagesRepository.save(customerSecurityImages);
    }
}
