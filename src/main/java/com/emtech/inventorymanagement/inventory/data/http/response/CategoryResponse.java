package com.emtech.inventorymanagement.inventory.data.http.response;

import com.emtech.inventorymanagement.inventory.data.category.CategoryData;
import lombok.*;
import org.springframework.http.HttpStatus;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    @Builder.Default
    private CategoryData category = null;

    @Builder.Default
    private Integer statusCode = HttpStatus.NOT_FOUND.value();
}
