package com.library.ebookstore.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.ebookstore.api.http.resources.request.BookRequest;
import com.library.ebookstore.api.http.resources.response.BookResponse;
import com.library.ebookstore.core.mapper.BookMapper;
import com.library.ebookstore.domain.model.Book;
import com.library.ebookstore.domain.service.BookService;
import com.library.ebookstore.domain.service.util.BookCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookMapper bookMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    BookService bookService;

    private Book book;

    private BookResponse bookResponse;

    private BookRequest bookRequest;

    @BeforeEach
    void setUp() {
        book = BookCreator.newBook();
        bookResponse = BookCreator.bookResponse();
        bookRequest = BookCreator.bookRequest();
    }

    @DisplayName("JUnit: BookController.createBook(book)")
    @Test
    void givenBookObject_whenCreateBook_thenReturnSavedBook() throws Exception {
        //given - precondition or setup
        given(bookMapper.mapRequestToEntity(any(BookRequest.class)))
                .willReturn(book);
        given(bookService.createBook(any(Book.class)))
                .willReturn(book);
        given(bookMapper.mapEntityToResponse(book))
                .willReturn(bookResponse);
        //when - action or behaviour to test
        ResultActions response = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest)));
        //then - verify the result
    }

}
