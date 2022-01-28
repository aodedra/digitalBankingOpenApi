package com.mob.casestudy.digitalbanking.repository;


import com.mob.casestudy.digitalbanking.entities.SecurityImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface SecurityImagesRepository extends JpaRepository<SecurityImages, UUID> {
}
