package com.emtech.inventorymanagement.inventory.data.category;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryData implements Serializable {
    @Builder.Default
    private Long id = null;

    @Builder.Default
    private String name = null;

    @Builder.Default
    private String description = null;

    @Builder.Default
    private Integer status = null;

    @Builder.Default
    private Timestamp creationDate = null;

    @Builder.Default
    private Timestamp updateDate = null;
}
