package com.example.emtlab1.model.dto;

import com.example.emtlab1.model.enumerations.Category;
import lombok.Data;

@Data
public class BooksDto {

    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;

    public BooksDto() {
    }

    public BooksDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
