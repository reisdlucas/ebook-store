package com.library.ebookstore.domain.service;

import com.library.ebookstore.domain.model.Book;

import java.util.List;

public interface BookService {
    Book create(Book book);

    List<Book> findAll();
}
