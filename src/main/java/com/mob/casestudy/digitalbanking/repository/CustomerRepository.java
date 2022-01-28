package com.mob.casestudy.digitalbanking.repository;

import com.mob.casestudy.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Customer findByUserName(String userName);

    boolean existsByUserName(String userName);
}
