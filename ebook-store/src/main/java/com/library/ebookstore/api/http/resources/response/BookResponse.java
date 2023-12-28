package com.library.ebookstore.api.http.resources.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    @Schema(description = "Book Id")
    private Long id;

    @Schema(description = "Book Name")
    private String name;

    @Schema(description = "Book Writer")
    private String writer;

    @Schema(description = "Book Company")
    private String company;

}
