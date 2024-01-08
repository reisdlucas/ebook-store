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

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private List<Book> bookList;

    private BookResponse bookResponse;

    private BookRequest bookRequest;

    @BeforeEach
    void setUp() {
        book = BookCreator.newBook();
        bookList = BookCreator.bookList();
        bookResponse = BookCreator.bookResponse();
        bookRequest = BookCreator.bookRequest();
    }

    @DisplayName("JUnit: BookController.createBook(book)")
    @Test
    void givenBookObject_whenCreateBook_thenReturnSavedBook() throws Exception {
        //given - precondition or setup
        given(bookMapper.mapRequestToEntity(any(BookRequest.class)))
                .willReturn(book);
        given(bookService.create(any(Book.class)))
                .willReturn(book);
        given(bookMapper.mapEntityToResponse(book))
                .willReturn(bookResponse);
        //when - action or behaviour to test
        ResultActions response = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest)));
        //then - verify the result
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(book.getId().intValue()))
                .andExpect(jsonPath("$.name").value(book.getName()))
                .andExpect(jsonPath("$.writer").value(book.getWriter()))
                .andExpect(jsonPath("$.company").value(book.getCompany()));
    }

    @DisplayName("JUnit: BookController.findAll()")
    @Test
    void givenBookList_whenFindAll_thenReturnBookList() throws Exception {
        //given - precondition or setup
        given(bookService.findAll()).willReturn(bookList);
        //when - action or behavior to test
        ResultActions response = mockMvc.perform(get("/book"));
        //then - verify the result
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(bookList.size())));
    }

}
