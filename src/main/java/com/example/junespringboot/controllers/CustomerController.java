package com.example.junespringboot.controllers;

import com.example.junespringboot.dao.CustomerDAO;
import com.example.junespringboot.models.Customer;
import com.example.junespringboot.models.dto.CustomerDTO;
import com.example.junespringboot.models.views.Views;
import com.example.junespringboot.services.CustomerService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerDAO customerDAO;
    private CustomerService customerService;

    @GetMapping("")
    @JsonView({Views.Client.class})
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = customerDAO.findAll();

        return new ResponseEntity<>(customers, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        Customer customer = customerDAO.findById(id).get();

        return new ResponseEntity<>(customer, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable int id) {
        customerDAO.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerDAO.findById(id).get();
        customer.setName(customerDTO.getUsername());
        customerDAO.save(customer);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String name) {


//        return new ResponseEntity<>(customerDAO.getByName(name),HttpStatusCode.valueOf(200));
//        return new ResponseEntity<>(customerDAO.findCustomerByName("kokos"),HttpStatusCode.valueOf(200));
//        return new ResponseEntity<>(customerService.customerListByName("kokos"), HttpStatusCode.valueOf(200));
        return customerService.customerListByName(name);
    }
}
