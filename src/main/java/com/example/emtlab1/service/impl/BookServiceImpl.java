package com.example.emtlab1.service.impl;

import com.example.emtlab1.model.Author;
import com.example.emtlab1.model.Book;
import com.example.emtlab1.model.dto.BooksDto;
import com.example.emtlab1.model.enumerations.Category;
import com.example.emtlab1.model.exceptions.AuthorNotFoundException;
import com.example.emtlab1.model.exceptions.BookNotFoundException;
import com.example.emtlab1.repository.AuthorRepository;
import com.example.emtlab1.repository.BookRepository;
import com.example.emtlab1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;



    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAllBooks() {
        return this.bookRepository.findAll();

    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);

    }

    @Override
    public Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(()-> new AuthorNotFoundException(authorId));
        Book b = new Book(name,category,author,availableCopies);
        this.bookRepository.save(b);
        return Optional.of(b);
    }

    @Override
    public Optional<Book> create(BooksDto booksDto) {
        Author author = this.authorRepository.findById(booksDto.getAuthor())
                .orElseThrow(()->new AuthorNotFoundException(booksDto.getAuthor()));

        this.bookRepository.deleteByName(booksDto.getName());
        Book book = new Book(booksDto.getName(),booksDto.getCategory(),author,booksDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book b = this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        b.setName(name);
        b.setCategory(category);
        Author author = this.authorRepository.findById(authorId).orElseThrow(()-> new AuthorNotFoundException(authorId));
        b.setAuthor(author);
        b.setAvailableCopies(availableCopies);

        this.bookRepository.save(b);
        return Optional.of(b);
    }

    @Override
    public Optional<Book> update(Long id, BooksDto booksDto) {
        Book b = this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        b.setName(booksDto.getName());
        b.setCategory(booksDto.getCategory());
        Author author = this.authorRepository.findById(booksDto.getAuthor())
                .orElseThrow(()-> new AuthorNotFoundException(booksDto.getAuthor()));
        b.setAuthor(author);
        this.bookRepository.save(b);
        return Optional.of(b);
    }

    @Override
    public void delete(Long id) {
        Book b = this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        this.bookRepository.delete(b);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book b = this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        Integer copies = b.getAvailableCopies();
        if(copies >0){
            b.setAvailableCopies(copies-1);
            this.bookRepository.save(b);
        }
        return Optional.of(b);
    }
}
