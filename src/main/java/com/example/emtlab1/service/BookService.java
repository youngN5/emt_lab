package com.example.emtlab1.service;

import com.example.emtlab1.model.Author;
import com.example.emtlab1.model.Book;
import com.example.emtlab1.model.dto.BooksDto;
import com.example.emtlab1.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAllBooks();

    Optional<Book> findById(Long id);

    Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> create(BooksDto booksDto);

    Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> update(Long id, BooksDto booksDto);

    void delete(Long id);

    Optional<Book> markAsTaken(Long id);
}
