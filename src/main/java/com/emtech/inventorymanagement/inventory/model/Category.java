package com.emtech.inventorymanagement.inventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@ToString
@Data
@EqualsAndHashCode(of = {"id"})
@DynamicUpdate
@Entity
@Table(name = "category_config", uniqueConstraints = {
        @UniqueConstraint(name = "catgeory_id", columnNames = {"id"}),
        @UniqueConstraint(name = "category_name", columnNames = {"name"})
})
public class Category implements Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", updatable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @CreationTimestamp
    @Column(name = "creation_date")
    @JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
    private Timestamp creationDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    @JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
    private Timestamp updateDate;

}
