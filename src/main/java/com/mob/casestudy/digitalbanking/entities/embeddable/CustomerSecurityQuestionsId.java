package com.mob.casestudy.digitalbanking.entities.embeddable;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CustomerSecurityQuestionsId implements Serializable {
    private UUID customerId;
    private UUID securityQuestionId;

    public CustomerSecurityQuestionsId(UUID customerId, UUID securityQuestionId) {
        this.customerId = customerId;
        this.securityQuestionId = securityQuestionId;
    }

    public CustomerSecurityQuestionsId() {
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getSecurityQuestionId() {
        return securityQuestionId;
    }

    @Override
    public String toString() {
        return "CustomerSecurityQuestionsId{" +
                "customerId='" + customerId + '\'' +
                ", securityQuestionId='" + securityQuestionId + '\'' + '}';
    }
}
