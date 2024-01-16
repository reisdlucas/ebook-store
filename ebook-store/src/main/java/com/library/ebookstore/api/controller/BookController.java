package com.library.ebookstore.api.controller;

import com.library.ebookstore.api.http.resources.request.BookRequest;
import com.library.ebookstore.api.http.resources.response.BookResponse;
import com.library.ebookstore.core.mapper.BookMapper;
import com.library.ebookstore.domain.model.Book;
import com.library.ebookstore.domain.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import java.util.List;
import java.util.stream.Collectors;

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
        Book bookSaved = bookService.create(book);

        URI uri = uriComponentsBuilder.path("/book/{id}")
                .buildAndExpand(bookSaved.getId())
                .toUri();
        BookResponse bookResponse = bookMapper.mapEntityToResponse(bookSaved);
        return ResponseEntity.created(uri).body(bookResponse);
    }
    @GetMapping
    @ResponseBody
    @Operation(description = "Find All Books", summary = "Find All Books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfull Operation",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookResponse.class)))),
    })
    public ResponseEntity<List<BookResponse>> findAllBooks() {
        List<Book> book = bookService.findAll();
        List<BookResponse> bookResponse = book.stream()
                .map(bookMapper::mapEntityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookResponse);
    }
    @GetMapping("/{idBook}")
    @Operation(description = "Find books by id", summary = "Find books by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfull Operation",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = BookResponse.class))}),
    })
    public ResponseEntity<BookResponse> findById(@PathVariable(name = "idBook") Long idBook) {
        Book book = bookService.findById(idBook);
        BookResponse bookResponse = bookMapper.mapEntityToResponse(book);
        return ResponseEntity.ok(bookResponse);
    }
    @PutMapping(value = "/{idBook}")
    @Operation(description = "Update book", summary = "Update book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfull Operation",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<BookResponse> update(@Parameter(description = "id book", required = true)
                                               @PathVariable(name = "idBook") Long idBook,
                                               @RequestBody BookRequest bookRequest) {
        Book book = bookMapper.mapRequestToEntity(bookRequest);
        Book update = bookService.update(idBook, book);
        BookResponse bookResponse = bookMapper.mapEntityToResponse(update);
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("/{idBook}")
    @Operation(description = "Delete book", summary = "Delete book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfull Operation"),
            @ApiResponse(responseCode = "404", description = "Bad Request")
    })
    public ResponseEntity<List<BookResponse>> delete(@Parameter(description = "id book", required = true)
                                                     @PathVariable(name = "idBook") Long idBook) {
        bookService.delete(idBook);
        return ResponseEntity.noContent().build();
    }

}
