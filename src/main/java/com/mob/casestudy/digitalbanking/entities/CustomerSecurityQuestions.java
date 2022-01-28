package com.mob.casestudy.digitalbanking.entities;

import com.mob.casestudy.digitalbanking.entities.embeddable.CustomerSecurityQuestionsId;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerSecurityQuestions {
    @EmbeddedId
    private CustomerSecurityQuestionsId  customerSecurityQuestionsId;
    private String securityQuestionAnswer;
    private LocalDateTime createdOn;
    @OneToOne
    @MapsId("customerId")
    private Customer customer;

    @OneToOne
    @MapsId("securityQuestionId")
    private SecurityQuestions securityQuestions;
}
