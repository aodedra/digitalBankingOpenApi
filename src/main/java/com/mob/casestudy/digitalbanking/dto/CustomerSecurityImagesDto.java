package com.mob.casestudy.digitalbanking.dto;

public class CustomerSecurityImagesDto {
    private String securityImageId;
    private String securityImageName;
    private String securityImageCaption;
    private String securityImageUrl;

    public CustomerSecurityImagesDto(String securityImageId,String securityImageName, String securityImageCaption, String securityImageUrl ) {
        this.securityImageId = securityImageId;
        this.securityImageName = securityImageName;
        this.securityImageCaption = securityImageCaption;
        this.securityImageUrl = securityImageUrl;
    }
    public String getSecurityImageId() {
        return securityImageId;
    }
    public String getSecurityImageCaption() {
        return securityImageCaption;
    }
    public String getSecurityImageUrl() {
        return securityImageUrl;
    }
    public String getSecurityImageName() {
        return securityImageName;
    }
}
