package com.example.junespringboot.dao;

import com.example.junespringboot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {

    List<Customer> findCustomerByName(String name);
    List<Customer> findCustomerBySurname(String name);
}
