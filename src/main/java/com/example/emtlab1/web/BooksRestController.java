package com.example.emtlab1.web;


import com.example.emtlab1.model.Book;
import com.example.emtlab1.model.dto.BooksDto;
import com.example.emtlab1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api")
public class BooksRestController {
    private final BookService bookService;

    public BooksRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/","/books"})
    private List<Book> findAll() {
        //TODO PAZI TUKA
        return this.bookService.listAllBooks();
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("books/add")
    public ResponseEntity<Book> addBook(@RequestBody BooksDto booksDto) {
        return this.bookService.create(booksDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("books/edit/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BooksDto booksDto) {
        return this.bookService.update(id, booksDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("books/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.delete(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("books/markAsTaken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id){
        this.bookService.markAsTaken(id);
        return ResponseEntity.ok().build();
    }
}
