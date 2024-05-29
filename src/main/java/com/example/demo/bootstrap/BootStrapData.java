package com.example.demo.bootstrap;

import com.example.demo.entities.Country;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BootStrapData implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Only add sample data if the customer count is 1 (based on given SQL script)
        long customerCount = ((Number) entityManager.createQuery("SELECT COUNT(c) FROM Customer c").getSingleResult()).longValue();
        if (customerCount == 1) {
            // Create and save new countries
            Country country1 = new Country();
            country1.setCountry_name("Country 1");
            entityManager.persist(country1);

            Country country2 = new Country();
            country2.setCountry_name("Country 2");
            entityManager.persist(country2);

            Country country3 = new Country();
            country3.setCountry_name("Country 3");
            entityManager.persist(country3);

            Country country4 = new Country();
            country4.setCountry_name("Country 4");
            entityManager.persist(country4);

            Country country5 = new Country();
            country5.setCountry_name("Country 5");
            entityManager.persist(country5);

            // Create and save new divisions
            Division division1 = new Division();
            division1.setDivision_name("Division 1");
            division1.setCountry(country1);
            entityManager.persist(division1);

            Division division2 = new Division();
            division2.setDivision_name("Division 2");
            division2.setCountry(country2);
            entityManager.persist(division2);

            Division division3 = new Division();
            division3.setDivision_name("Division 3");
            division3.setCountry(country3);
            entityManager.persist(division3);

            Division division4 = new Division();
            division4.setDivision_name("Division 4");
            division4.setCountry(country4);
            entityManager.persist(division4);

            Division division5 = new Division();
            division5.setDivision_name("Division 5");
            division5.setCountry(country5);
            entityManager.persist(division5);

            // Create and save new customers
            Customer customer1 = new Customer("Drew", "Kelly", "789 Cedar Rd", "67890", "555-123-4567", division1);
            Customer customer2 = new Customer("Jane", "Roe", "456 Birch Ave", "23456", "555-234-5678", division2);
            Customer customer3 = new Customer("Michael", "Smith", "123 Maple Dr", "34567", "555-345-6789", division3);
            Customer customer4 = new Customer("Emily", "Johnson", "789 Oak Ln", "45678", "555-456-7890", division4);
            Customer customer5 = new Customer("David", "Williams", "456 Pine St", "56789", "555-567-8901", division5);

            entityManager.persist(customer1);
            entityManager.persist(customer2);
            entityManager.persist(customer3);
            entityManager.persist(customer4);
            entityManager.persist(customer5);
        }
    }
}
