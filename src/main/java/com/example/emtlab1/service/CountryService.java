package com.example.emtlab1.service;

import com.example.emtlab1.model.Author;
import com.example.emtlab1.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> listAllCountries();

    Country findById(Long id);

    Country create(String name, String continent);

    Country update(Long id, String name, String continent);

    void delete(Long id);
}
