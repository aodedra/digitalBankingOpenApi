package com.mob.casestudy.digitalbanking.repository;

import com.mob.casestudy.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByUserName(String userName);
}
