package com.library.ebookstore.domain.service.unit;

import com.library.ebookstore.domain.model.Book;
import com.library.ebookstore.domain.repository.BookRepository;
import com.library.ebookstore.domain.service.BookServiceImpl;
import com.library.ebookstore.domain.service.util.BookCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;
    private final Book book = BookCreator.newBook();

    @DisplayName("JUnit: BookService.create(Book)")
    @Test
    void givenBook_whenCreate_thenReturnSavedBook() {
        //given - precondition or setup
        given(bookRepository.save(book)).willReturn(book);
        //when - action or the behavior to test
        Book savedBook = bookService.createBook(book);
        //then - verify the output
        assertThat(savedBook).isNotNull();
    }

}
