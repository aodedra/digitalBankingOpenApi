package com.mob.casestudy.digitalbanking.dto;


public class CustomerOtpDto {
    private String userName;
    private String otp;

    public CustomerOtpDto() {
    }

    public CustomerOtpDto(String userName, String otp) {
        this.userName = userName;
        this.otp = otp;
    }

    public String getUserName() {
        return userName;
    }

    public String getOtp() {
        return otp;
    }
}
