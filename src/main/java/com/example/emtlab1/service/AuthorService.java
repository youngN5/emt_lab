package com.example.emtlab1.service;

import com.example.emtlab1.model.Author;
import com.example.emtlab1.model.Country;

import java.util.List;

public interface AuthorService {

    List<Author> listAllAuthors();

    Author findById(Long id);

    Author create(String name, String surname, Long countryId);

    Author update(Long id, String name, String surname, Long countryId);

    void delete(Long id);
}
