package com.emtech.inventorymanagement.inventory.data.http.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateRequest {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;
}
