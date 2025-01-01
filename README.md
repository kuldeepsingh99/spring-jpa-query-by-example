# Spring Data JPA Query by Example
This repository demonstrates how to use Query by Example (QBE) with Spring Data JPA, a powerful feature for building dynamic queries based on the properties of a probe (an example object).

# Overview
Query by Example allows developers to:

- Create dynamic queries without writing custom query methods or JPQL.
- Use an example (probe) entity as the basis for matching criteria.
- Combine with Sort and Pageable for advanced query capabilities.
- This approach is type-safe and minimizes boilerplate code while providing a straightforward way to query entities.

# Prerequisites
Ensure the following tools and dependencies are set up:

- Java 8 or later
- Maven or Gradle
- Spring Boot 2.x or later
- Spring Data JPA

# Additional Features of Query by Example
## Default Matching Behavior
The default behavior of QBE matches all non-null fields in the probe object using exact matching.

Only fields set on the probe object are included in the query.
Null fields are ignored.

Example:
```
Customer probe = new Customer();
probe.setFirstName("John"); // Matches "John" exactly
probe.setLastName(null);    // Ignored in the query

Example<Customer> example = Example.of(probe);
List<Customer> result = customerRepository.findAll(example);
```

## Customizing Example Matching with ExampleMatcher
The ExampleMatcher allows fine-grained control over how fields are matched. You can:

- Ignore specific fields.
- Apply partial matching (e.g., LIKE, STARTING_WITH, ENDING_WITH).
- Make matching case-insensitive.

Example:
```
ExampleMatcher matcher = ExampleMatcher.matching()
    .withIgnorePaths("email") // Ignores the 'email' field
    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Matches substrings
    .withIgnoreCase(); // Case-insensitive matching

Customer probe = new Customer();
probe.setFirstName("john"); // Matches "John", "JOHN", etc.
probe.setLastName("doe");   // Matches "Doe", "DOE", etc.

Example<Customer> example = Example.of(probe, matcher);
List<Customer> result = customerRepository.findAll(example);
```

## Example Without Query By Example

To query customers dynamically (e.g., by name, email, and age), you would typically write custom queries or method names in your repository.

**Repository**
```
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameAndEmailAndAge(String name, String email, Integer age);
}
```

**Service**
```
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findCustomers(String name, String email, Integer age) {
        // If all parameters must be non-null
        if (name == null || email == null || age == null) {
            throw new IllegalArgumentException("All parameters are required!");
        }

        return customerRepository.findByNameAndEmailAndAge(name, email, age);
    }
}
```
### Problems with This Approach
- **Hardcoded Query Logic**: Every combination of search criteria requires a new repository method or a custom query.
- **Scalability Issues**: Adding a new filter means updating the repository and service logic.
- **Boilerplate Code**: Manual null-checking and query-building increase code verbosity.



