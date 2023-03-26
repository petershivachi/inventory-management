package com.emtech.inventorymanagement.inventory.data;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntityResponse implements Serializable {
    @Builder.Default
    private Integer statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    @Builder.Default
    private String message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
}
