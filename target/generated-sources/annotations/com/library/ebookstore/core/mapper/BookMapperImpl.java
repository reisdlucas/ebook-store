package com.library.ebookstore.core.mapper;

import com.library.ebookstore.api.http.resources.request.BookRequest;
import com.library.ebookstore.api.http.resources.response.BookResponse;
import com.library.ebookstore.domain.model.Book;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-29T13:52:07-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book mapRequestToEntity(BookRequest bookRequest) {
        if ( bookRequest == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.name( bookRequest.getName() );
        book.writer( bookRequest.getWriter() );
        book.company( bookRequest.getCompany() );

        return book.build();
    }

    @Override
    public BookResponse mapEntityToResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponse.BookResponseBuilder bookResponse = BookResponse.builder();

        bookResponse.id( book.getId() );
        bookResponse.name( book.getName() );
        bookResponse.writer( book.getWriter() );
        bookResponse.company( book.getCompany() );

        return bookResponse.build();
    }
}
