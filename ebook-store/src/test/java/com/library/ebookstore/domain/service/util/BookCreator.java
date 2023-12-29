package com.library.ebookstore.domain.service.util;

import com.library.ebookstore.domain.model.Book;

public class BookCreator {
    public static Book newBook() {
        return Book.builder()
                .id(1L)
                .name("name")
                .writer("writer")
                .company("company")
                .build();
    }
}
