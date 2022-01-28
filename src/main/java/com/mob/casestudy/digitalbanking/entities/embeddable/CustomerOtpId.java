package com.mob.casestudy.digitalbanking.entities.embeddable;


import javax.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CustomerOtpId implements Serializable {
    private UUID customerId;
    private UUID otpId;

    public CustomerOtpId() {
        otpId=UUID.randomUUID();
    }

    public CustomerOtpId(UUID customerId, UUID otpId) {
        this.customerId = customerId;
        this.otpId = otpId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getOtpId() {
        return otpId;
    }

    public void setOtpId(UUID otpId) {
        this.otpId = otpId;
    }
}
