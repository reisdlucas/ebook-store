package com.library.ebookstore.api.http.resources.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotBlank(message = "Can't be blank")
    @Schema(description = "Book name")
    private String name;

    @NotBlank(message = "Can't be blank")
    @Schema(description = "Book Writer")
    private String writer;

    @NotBlank(message = "Can't be blank")
    @Schema(description = "Book Company")
    private String company;


}
