package com.library.ebookstore.core.mapper;

import com.library.ebookstore.api.http.resources.request.BookRequest;
import com.library.ebookstore.api.http.resources.response.BookResponse;
import com.library.ebookstore.domain.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    Book mapRequestToEntity(BookRequest bookRequest);
    @Mapping(source = "id", target = "id")
    BookResponse mapEntityToResponse(Book book);
}
