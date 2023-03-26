package com.emtech.inventorymanagement.inventory.data.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest implements Serializable {
    @JsonProperty(value = "category_id")
    private Long categoryId;

    @JsonProperty(value = "description")
    private String description;

}
