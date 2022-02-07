package com.mob.casestudy.digitalbanking.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String status;
    private String preferredLanguages;
    private String externalId;
    private String createdBy;
    private String createdOn;
    private String updatedBy;
    private String updatedOn;
}
