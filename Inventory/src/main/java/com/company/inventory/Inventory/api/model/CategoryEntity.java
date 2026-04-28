package com.company.inventory.Inventory.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategory")
    private Integer idcategory;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

}
