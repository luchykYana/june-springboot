package com.example.junespringboot.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.junespringboot.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer  c where  c.name=:name")
    List<Customer> getByName(@Param("name") String name);

    List<Customer> findCustomerByName(String name);
}
