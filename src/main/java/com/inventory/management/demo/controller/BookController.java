package com.inventory.management.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.management.demo.entity.Book;
import com.inventory.management.demo.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookController {
    @Autowired
    private BookRepository repository;

    @GetMapping(value = "/books/{id}",produces =APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBookById(@PathVariable Long id) {
        Optional<Book> book = repository.findById(Long.valueOf(id));
        if (!book.isEmpty()) {
            log.info("book found with book id : {}", id);
            ObjectMapper mapper = new ObjectMapper();
            try {
                return ResponseEntity.ok().body(mapper.writeValueAsString(book.get()));
            } catch (JsonProcessingException e) {
                return new ResponseEntity<>("book id not found", HttpStatus.NOT_FOUND);
            }
        } else {
            log.info("no book found for book id : {}", id);
            return new ResponseEntity<>("book id not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/books" ,produces =APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = repository.findAll();
        return ResponseEntity.ok(books);
    }

    @PostMapping(value = "/books" ,
            produces =APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        repository.save(book);
        log.info("New Book saved with book id :{}", book.getId());
        return ResponseEntity.ok(book);
    }

    @PutMapping(value = "/books/{bid}" , consumes = APPLICATION_JSON_VALUE,
            produces =APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Optional<Book> bookOpt = repository.findById(book.getId());
        Book bookFromDB = bookOpt.get();
        if(bookFromDB != null)
            bookFromDB.setName(book.getName());
            bookFromDB.setPrice(book.getPrice());
        repository.flush();
        log.info("Book saved with new value: {}", book.getId());
        return ResponseEntity.ok(book);
    }

    @DeleteMapping(value = "/books/{bid}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable String bid){
        repository.deleteById(Long.valueOf(bid));
        log.info("Book deleted with book id :{}", bid);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}