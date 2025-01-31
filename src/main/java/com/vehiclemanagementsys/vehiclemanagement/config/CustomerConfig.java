package com.vehiclemanagementsys.vehiclemanagement.config;

import com.vehiclemanagementsys.vehiclemanagement.model.Customer;
import com.vehiclemanagementsys.vehiclemanagement.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository) {
        return args -> {
            Customer tywaine = new Customer(
                    "Tywaine",
                    "Peters",
                    "773-2996",
                    "peterstywaine@gmail.com",
                    "17 Petunia Way Mona Kingston 6.",
                    LocalDateTime.now()
            );

            customerRepository.saveAll(List.of(tywaine));
        };
    }
}
