package com.li.micro.course.inventoryservice;

import com.li.micro.course.inventoryservice.model.Inventory;
import com.li.micro.course.inventoryservice.repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo) {
		return (args) -> {
			if(!inventoryRepo.findAll().isEmpty()) {
				return;
			}
			inventoryRepo.save(new Inventory("iphone_13", 100));
			inventoryRepo.save(new Inventory("iphone_13_red", 0));
			inventoryRepo.save(new Inventory("iphone_12", 90));
			inventoryRepo.save(new Inventory("iphone_11", 80));
			inventoryRepo.save(new Inventory("iphone_10", 70));
			inventoryRepo.save(new Inventory("iphone_9", 60));
		};
	}

}
