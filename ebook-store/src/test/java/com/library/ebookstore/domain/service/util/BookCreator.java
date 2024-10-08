package com.library.ebookstore.domain.service.util;

import com.library.ebookstore.api.http.resources.request.BookRequest;
import com.library.ebookstore.api.http.resources.response.BookResponse;
import com.library.ebookstore.domain.model.Book;

import java.util.Arrays;
import java.util.List;

public class BookCreator {
    public static Book newBook() {
        return Book.builder()
                .id(1L)
                .name("name")
                .writer("writer")
                .company("company")
                .build();
    }

    public static BookRequest bookRequest() {
        return BookRequest.builder()
                .name(newBook().getName())
                .writer(newBook().getWriter())
                .company(newBook().getCompany())
                .build();
    }

    public static BookResponse bookResponse() {
        return BookResponse.builder()
                .id(newBook().getId())
                .name(newBook().getName())
                .writer(newBook().getWriter())
                .company(newBook().getCompany())
                .build();
    }

    public static List<Book> bookList() {
        Book savedBook1 = newBook();
        Book savedBook2 = newBook();
        savedBook2.setId(2L);
        return Arrays.asList(savedBook1, savedBook2);
    }
}
