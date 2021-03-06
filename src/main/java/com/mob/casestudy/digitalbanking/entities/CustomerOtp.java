package com.mob.casestudy.digitalbanking.entities;

import com.mob.casestudy.digitalbanking.entities.embeddable.CustomerOtpId;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerOtp {

    @EmbeddedId
    private CustomerOtpId customerOtpId=new CustomerOtpId();
    private String otpMessage;
    private String otp;
    private Integer otpRetries;
    private LocalDateTime expiryOn;
    private LocalDateTime createdOn;
    @OneToOne(cascade= CascadeType.REMOVE)
    @MapsId("customerId")
    private Customer customer;
}
