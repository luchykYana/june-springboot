package com.example.junespringboot.controllers;

import com.example.junespringboot.models.Customer;
import com.example.junespringboot.models.dto.CustomerDTO;
import com.example.junespringboot.models.views.Views;
import com.example.junespringboot.services.CustomerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService service;

    @GetMapping("")
    @JsonView(Views.Client.class)
    public ResponseEntity<List<Customer>> getAll(){
        return service.getAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Customer customer){
        service.create(customer);
    }

    @GetMapping("/{id}")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Customer> getOne(@PathVariable int id){
        Customer customer = service.getCustomerById(id);

        return new ResponseEntity<>(customer, HttpStatusCode.valueOf(200));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable int id, @RequestBody CustomerDTO customer){
        service.update(id, customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable String name) {
        List<Customer> customers = service.getCustomersByName(name);

        return new ResponseEntity<>(customers, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<List<Customer>> getCustomersBySurname(@PathVariable String surname) {
        List<Customer> customers = service.getCustomersBySurname(surname);

        return new ResponseEntity<>(customers, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/activate/{id}")
    public void activate(@PathVariable int id) {
        Customer customer = service.getCustomerById(id);
        customer.setActivated(true);
        service.update(customer);
    }
}
