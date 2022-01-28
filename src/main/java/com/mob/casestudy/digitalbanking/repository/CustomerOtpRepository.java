package com.mob.casestudy.digitalbanking.repository;

import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.entities.embeddable.CustomerOtpId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOtpRepository extends JpaRepository<CustomerOtp, CustomerOtpId> {
    CustomerOtp findByOtp(String otp);
}
