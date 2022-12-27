package com.example.junespringboot.services;

import com.example.junespringboot.dao.CustomerDAO;
import com.example.junespringboot.models.Customer;
import com.example.junespringboot.models.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerDAO dao;
    private MailService mailService;

    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> all = dao.findAll();

        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    public void create(Customer customer){
        dao.save(customer);

        mailService.send(customer);
    }

    public Customer getCustomerById(int id){
        return dao.findById(id).get();
    }

    public void update(int id, CustomerDTO customer) {
        Customer c = dao.findById(id).get();

        c.setName(customer.getName());
        c.setSurname(customer.getSurname());
        c.setEmail(customer.getEmail());

        dao.save(c);
    }

    public void update(Customer customer) {
        dao.save(customer);
    }

    public void delete(int id) {
        dao.deleteById(id);
    }

    public List<Customer> getCustomersByName(String name) {
        if (name != null && !name.isBlank()) {
            return dao.findCustomerByName(name);
        } else {
            throw new RuntimeException();
        }
    }

    public List<Customer> getCustomersBySurname(String surname) {
        if (surname != null && !surname.isBlank()) {
            return dao.findCustomerBySurname(surname);
        } else {
            throw new RuntimeException();
        }
    }
}
