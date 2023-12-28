package com.library.ebookstore.api.controller;

import com.library.ebookstore.api.http.resources.request.BookRequest;
import com.library.ebookstore.api.http.resources.response.BookResponse;
import com.library.ebookstore.core.mapper.BookMapper;
import com.library.ebookstore.domain.model.Book;
import com.library.ebookstore.domain.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/book")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Book", description = "Book resources")
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    @Operation(description = "Create Book", summary = "Create Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Seccessfull Operattion",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Bad Request")
    })
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookRequest bookRequest,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        Book book = bookMapper.mapRequestToEntity(bookRequest);
        Book bookSaved = bookService.createBook(book);

        URI uri = uriComponentsBuilder.path("/book/{id}")
                .buildAndExpand(bookSaved.getId())
                .toUri();
        BookResponse bookResponse = bookMapper.mapEntityToResponse(bookSaved);
        return ResponseEntity.created(uri).body(bookResponse);
    }
}
