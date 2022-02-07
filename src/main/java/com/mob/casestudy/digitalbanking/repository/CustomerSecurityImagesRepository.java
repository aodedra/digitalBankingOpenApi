package com.mob.casestudy.digitalbanking.repository;

import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.embeddable.CustomerSecurityImagesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerSecurityImagesRepository extends JpaRepository<CustomerSecurityImages, CustomerSecurityImagesId> {
}
