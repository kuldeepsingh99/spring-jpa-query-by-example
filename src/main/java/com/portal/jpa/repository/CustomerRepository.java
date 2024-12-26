package com.portal.jpa.repository;

import com.portal.jpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>,
        QueryByExampleExecutor<Customer>,
        JpaSpecificationExecutor<Customer> {

    @Query("SELECT e FROM Customer e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(e.address) LIKE LOWER(CONCAT('%', :address, '%'))")
    List<Customer> findByNameOrAddress(@Param("name") String name, @Param("address") String address);
}