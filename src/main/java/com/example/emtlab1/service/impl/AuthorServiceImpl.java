package com.example.emtlab1.service.impl;

import com.example.emtlab1.model.Author;
import com.example.emtlab1.model.Country;
import com.example.emtlab1.model.exceptions.AuthorNotFoundException;
import com.example.emtlab1.repository.AuthorRepository;
import com.example.emtlab1.repository.CountryRepository;
import com.example.emtlab1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Author> listAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        Country country = this.countryRepository.getById(countryId);
        Author author = new Author(name, surname, country);
        this.authorRepository.save(author);
        return author;
    }

    @Override
    public Author update(Long id, String name, String surname, Long countryId) {
        Author author = this.findById(id);
        author.setName(name);
        author.setSurname(surname);
        Country country = this.countryRepository.getById(countryId);
        author.setCountry(country);
        this.authorRepository.save(author);
        return author;
    }

    @Override
    public void delete(Long id) {
        Author author = this.findById(id);
        this.authorRepository.delete(author);
    }
}
