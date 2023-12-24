package com.library.ebookstore.domain.service;

import com.library.ebookstore.domain.model.Book;
import com.library.ebookstore.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Long idBook, Book book) {
        return bookRepository.save(book);
    }
}
