package com.example.emtlab1.service.impl;

import com.example.emtlab1.model.Country;
import com.example.emtlab1.model.exceptions.CountryNotFoundException;
import com.example.emtlab1.repository.CountryRepository;
import com.example.emtlab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> listAllCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(()-> new CountryNotFoundException(id));
    }

    @Override
    public Country create(String name, String continent) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Country c = new Country(name,continent);
        countryRepository.save(c);
        return c;
    }

    @Override
    public Country update(Long id, String name, String continent) {
        Country c =  this.findById(id);
        c.setName(name);
        c.setContinent(continent);
        countryRepository.save(c);
        return c;
    }

    @Override
    public void delete(Long id) {
        Country c = this.findById(id);
        this.countryRepository.delete(c);
    }
}
