package com.inventory.management.demo;

import com.inventory.management.demo.entity.Book;
import com.inventory.management.demo.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
@Slf4j
public class BookServiceApplication {
	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}


	//@Bean
	public CommandLineRunner run() throws Exception {

		return args -> {
			repository.deleteAll();
			Book a1 = new Book(100l,"Book",100.45);
			Book a2 = new Book(200l,"Table",2000.50);
			Book a3 = new Book(300l,"Tea",100.50);
			Book a4 = new Book(400l,"Mobile",500.35);
			List<Book> authorList =Arrays.asList(a1,a2,a3,a4);
			repository.saveAll(authorList);
			log.info("Data initilization competed for book service");
		};
	}
}
