package com.library.ebookstore.domain.service;

import com.library.ebookstore.domain.exception.NotFoundException;
import com.library.ebookstore.domain.model.Book;
import com.library.ebookstore.domain.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public Book update(Long idBook, Book book) {
        Book bookToUpdate = findById(idBook);
        BeanUtils.copyProperties(book, bookToUpdate, "id","book");
        return bookRepository.save(bookToUpdate);
    }

    @Override
    public void delete(Long idBook) {
        Book book = findById(idBook);
        bookRepository.delete(book);
    }
}
