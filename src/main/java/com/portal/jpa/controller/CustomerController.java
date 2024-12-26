package com.portal.jpa.controller;

import com.portal.jpa.model.Customer;
import com.portal.jpa.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customerByExample")
    public List<Customer> getCustomerByExample(@RequestBody Customer customer) {
        return customerService.getCustomerByExample(customer);
    }

    @GetMapping("/customerNameAndAddress")
    public List<Customer> getCustomerByNameAndAddress(@RequestParam String name,
                                                      @RequestParam String address) {
        return customerService.getCustomerByNameAndAddress(name, address);
    }

    @GetMapping("/customerSalaryAndActive")
    public List<Customer> getCustomerBySalaryGreaterThan(@RequestParam Double salary,
                                                         @RequestParam Integer active) {
        return customerService.getCustomerBySalaryGreaterThan(salary, active);
    }

    @GetMapping("/customerNameOrAddress")
    public List<Customer> hasNameOrAddress(@RequestParam String name,
                                           @RequestParam String address) {
        return customerService.hasNameOrAddress(name, address);
    }

    @GetMapping("/customerNameOrAddress/v1")
    public List<Customer> findCustomerNameOrAddress(@RequestParam String name,
                                           @RequestParam String address) {
        return customerService.findByNameOrAddress(name, address);
    }
}
