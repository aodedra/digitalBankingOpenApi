package com.mob.casestudy.digitalbanking;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.entities.embeddable.CustomerOtpId;
import com.mob.casestudy.digitalbanking.entities.enumrator.Language;
import com.mob.casestudy.digitalbanking.entities.enumrator.UserStatus;
import com.mob.casestudy.digitalbanking.repository.CustomerOtpRepository;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import com.mob.casestudy.digitalbanking.staticdatainsertion.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
public class DigitalbankingApplication implements CommandLineRunner {

    private final CustomerService customerServices;
    private final CustomerOtpRepository customerOtpRepository;
    private final CustomerRepository customerRepository;

    public DigitalbankingApplication(CustomerService customerServices, CustomerOtpRepository customerOtpRepository, CustomerRepository customerRepository) {
        this.customerServices = customerServices;
        this.customerOtpRepository = customerOtpRepository;
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DigitalbankingApplication.class, args);
    }
    @Override
    @Transactional
    public void run(String... args){
        customerServices.addCustomer();
        Customer customer = Customer.builder().userName("Avada123").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).status(UserStatus.ACTIVE).build();
        customerRepository.save(customer);
        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("This is Opt").otp("123456").otpRetries(1).createdOn(LocalDateTime.now()).expiryOn(LocalDateTime.of(LocalDate.of(2023,1,31), LocalTime.of(12,5,48,346682))).build();
        customerOtp.setCustomer(customer);
        customerOtpRepository.save(customerOtp);
    }
}
