package com.ust.restapicrudexample.persistance;

import com.ust.restapicrudexample.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
