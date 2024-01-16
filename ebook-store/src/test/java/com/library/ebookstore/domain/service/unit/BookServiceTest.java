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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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
        Book savedBook = bookService.create(book);
        //then - verify the output
        assertThat(savedBook).isNotNull();
    }

    @DisplayName("JUnit: BookService.findAll()")
    @Test
    void givenBookList_whenFindAllBooks_thenReturnBookList() {
        //given - precondition or setup
        List<Book> bookList = BookCreator.bookList();
        given(bookRepository.findAll()).willReturn(bookList);
        //when - action or behavior to test
        List<Book> list = bookService.findAll();
        //then - verify the output
        assertThat(list)
                .isNotNull()
                .hasSizeGreaterThan(1);
    }

    @DisplayName("JUnit: BookService.findById(Long)")
    @Test
    void givenBookId_whenFindBookById_thenReturnBookObject() {
        //given - precondition or setup
        given(bookRepository.findById(book.getId())).willReturn(Optional.of(book));
        //when - action or behavior to test
        Book savedBook = bookService.findById(book.getId());
        //then - verify the output
        assertThat(savedBook).isNotNull();
    }

    @DisplayName("JUnit: BookService.update(idBook, book)")
    @Test
    void givenBookObject_whenUpdateBook_thenReturnUpdatedBook() {
        //given - precondition or setup
        given(bookRepository.findById(book.getId())).willReturn(Optional.of(book));
        given(bookRepository.save(book)).willReturn(book);
        //when - action or behavior to test
        Book updatedBook = bookService.update(1L, book);
        //then - verify the output
        assertThat(updatedBook.getName()).isEqualTo("name");
        assertThat(updatedBook.getWriter()).isEqualTo("writer");
        assertThat(updatedBook.getCompany()).isEqualTo("company");
    }

    @DisplayName("JUnit: BookService.delete(idBook)")
    @Test
    void givenBookId_whenDeleteBook_thenBookIsDeleted() {
        //given - precondition or setup
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        //when - action or behavior to test
        bookService.delete(book.getId());
        //then - verify the output
        verify(bookRepository).delete(book);
    }
}
