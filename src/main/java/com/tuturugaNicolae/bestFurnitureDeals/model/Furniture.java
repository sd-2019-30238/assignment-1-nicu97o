package com.tuturugaNicolae.bestFurnitureDeals.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Deal> deals;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<BoughtFurniture> boughtFurniture;

    public Furniture(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
