package com.example.junespringboot.services;

import com.example.junespringboot.dao.CustomerDAO;
import com.example.junespringboot.models.Customer;
import com.example.junespringboot.models.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService {
    private CustomerDAO customerDAO;

    public void saveCustomer(Customer customer) {
        System.out.println(customer);
        if (customer.getName().length() > 0) {
            customerDAO.save(customer);
        } else {
            throw new RuntimeException("id not valid");
        }
    }

    public ResponseEntity<List<Customer>> customerListByName(String name) {
        if (name != null && !name.isBlank()) {
            List<Customer> customerByName = customerDAO.findCustomerByName(name);
            System.out.println(customerByName);
            return new ResponseEntity<>(customerByName, HttpStatusCode.valueOf(200));
        } else {
            throw new RuntimeException();
        }


    }
}
