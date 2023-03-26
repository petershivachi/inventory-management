package com.emtech.inventorymanagement.inventory.data.http.response;

import com.emtech.inventorymanagement.inventory.data.category.CategoryData;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriesResponse implements Serializable {
    @Builder.Default
    private List<CategoryData> categories = null;

    @Builder.Default
    private Integer statusCode = HttpStatus.NOT_FOUND.value();
}
