package com.portal.jpa.service;

import com.portal.jpa.model.Customer;
import com.portal.jpa.repository.CustomerRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.portal.jpa.model.Customer.*;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomerByExample(Customer customer) {
        return customerRepository.findAll(Example.of(customer));
    }

    public List<Customer> getCustomerByNameAndAddress(String name, String address) {

        Customer customer = Customer.builder()
                .address(address)
                .name(name)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("address", ExampleMatcher.GenericPropertyMatcher::contains);

        return customerRepository.findAll(Example.of(customer, matcher));

    }

    public List<Customer> getCustomerBySalaryGreaterThan(Double salary, Integer active) {

        Specification<Customer> spec = (root, query, criteriaBuilder) -> {
            Predicate salaryPredicate = criteriaBuilder.greaterThan(root.get("salary"), salary);
            Predicate activePredicate = criteriaBuilder.equal(root.get("active"), active);
            return criteriaBuilder.and(salaryPredicate, activePredicate);
        };
        return customerRepository.findAll(spec);
    }

    public List<Customer> hasNameOrAddress(String name, String address) {
        Specification<Customer> spec = (root, query, criteriaBuilder) -> {
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            Predicate addressPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%");
            return criteriaBuilder.or(namePredicate, addressPredicate);
        };
        return customerRepository.findAll(spec);
    }

    public List<Customer> findByNameOrAddress(String name, String address) {
        return customerRepository.findByNameOrAddress(name, address);
    }

}
