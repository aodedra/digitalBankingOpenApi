package com.mob.casestudy.digitalbanking.entities;

import com.mob.casestudy.digitalbanking.entities.enumrator.Language;
import com.mob.casestudy.digitalbanking.entities.enumrator.UserStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @Column(length = 36)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(length = 30)
    private String userName;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 10)
    private String phoneNumber;
    @Column(length = 50)
    private String email;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    private Language preferredLanguages;
    @Column(length = 50)
    private String externalId;
    @Column(length = 50)
    private String createdBy;
    private LocalDateTime createdOn;
    @Column(length = 50)
    private String updatedBy;
    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "customer")
    private List<CustomerSecurityQuestions> customerSecurityQuestions = new ArrayList<>();

    @OneToOne(mappedBy = "customer")
    private CustomerSecurityImages customerSecurityImages;

    @OneToOne(mappedBy = "customer")
    private CustomerOtp customerOtp;


}
