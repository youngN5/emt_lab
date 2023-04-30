package com.example.emtlab1.repository;

import com.example.emtlab1.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //TODO: PAZI TUKA
    void deleteByName(String name);
    /*Page<Book> findAll(Pageable pageable);*/
}
