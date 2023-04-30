package com.example.emtlab1;

import com.example.emtlab1.model.Country;
import com.example.emtlab1.model.dto.BooksDto;
import com.example.emtlab1.model.enumerations.Category;
import com.example.emtlab1.service.AuthorService;
import com.example.emtlab1.service.BookService;
import com.example.emtlab1.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class EmtLab1Application {
    private final CountryService countryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public EmtLab1Application(CountryService countryService, AuthorService authorService, BookService bookService) {
        this.countryService = countryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmtLab1Application.class, args);
    }

    @PostConstruct
    public void init() {
        Country country = countryService.create("United Kingdom","Europe");
        Country country1 = countryService.create("Russia", "Europe");
        authorService.create("William","Shakespeare",country.getId());
        authorService.create("Agatha", "Christie", country.getId());

        authorService.create("Fyodor", "Dostoevsky", country1.getId());

        bookService.create(new BooksDto("Book1", Category.FANTASY, 1L, 2));
        bookService.create(new BooksDto("Book2", Category.FANTASY, 1L, 5));
        bookService.create(new BooksDto("Book3", Category.FANTASY, 1L, 2));
        bookService.create(new BooksDto("Book4", Category.FANTASY, 1L, 5));
        bookService.create(new BooksDto("Book5", Category.FANTASY, 1L, 5));
        bookService.create(new BooksDto("Book6", Category.FANTASY, 1L, 5));
        bookService.create(new BooksDto("Book7", Category.FANTASY, 1L, 3));
        bookService.create(new BooksDto("Book8", Category.FANTASY, 1L, 5));

        bookService.create(new BooksDto("Book9", Category.FANTASY, 3L, 5));
        bookService.create(new BooksDto("Book10", Category.FANTASY, 3L, 3));
        bookService.create(new BooksDto("Book11", Category.FANTASY, 3L, 3));
        bookService.create(new BooksDto("Book12", Category.FANTASY, 3L, 3));
        bookService.create(new BooksDto("Book13", Category.FANTASY, 3L, 3));
        bookService.create(new BooksDto("Book14", Category.FANTASY, 3L, 5));
        bookService.create(new BooksDto("Book15", Category.FANTASY, 3L, 5));


    }
}
