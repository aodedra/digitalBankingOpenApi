package com.mob.casestudy.digitalbanking.entities.embeddable;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CustomerSecurityImagesId implements Serializable {

    private UUID customerId;
    private UUID securityImageId;

    public CustomerSecurityImagesId(UUID customerId, UUID securityImageId) {
        this.customerId = customerId;
        this.securityImageId = securityImageId;
    }
    public CustomerSecurityImagesId() {

    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getSecurityImageId() {
        return securityImageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSecurityImagesId that = (CustomerSecurityImagesId) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(securityImageId, that.securityImageId);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
