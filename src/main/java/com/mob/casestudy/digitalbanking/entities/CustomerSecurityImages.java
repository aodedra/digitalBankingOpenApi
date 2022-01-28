package com.mob.casestudy.digitalbanking.entities;

import com.mob.casestudy.digitalbanking.dto.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.entities.embeddable.CustomerSecurityImagesId;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
public class CustomerSecurityImages {
    @EmbeddedId
    private CustomerSecurityImagesId customerSecurityImagesId =new CustomerSecurityImagesId();
    private String securityImageCaption;
    private LocalDateTime createdOn;

    @OneToOne
    @MapsId("customerId")
    private Customer customer;

    @OneToOne
    @MapsId("securityImageId")
    private SecurityImages securityImages;

    public CustomerSecurityImagesDto toDto() {
        return new CustomerSecurityImagesDto(customerSecurityImagesId.getSecurityImageId().toString(), securityImages.getSecurityImageName(), securityImageCaption, securityImages.getSecurityImageUrl());
    }


}
