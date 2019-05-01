package com.bestfurnituredeals.assignment3.model.database;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @Column
    private BigDecimal price;

    @Column
    private boolean available;

    @Column
    private int availableQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "furnitureId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @EqualsAndHashCode.Exclude
    private Furniture furniture;

    @OneToMany(mappedBy = "deal", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Product> products;

    public Deal(String name, DealType dealType, BigDecimal price, boolean available, int availableQuantity) {
        this.name = name;
        this.dealType = dealType;
        this.price = price;
        this.available = available;
        this.availableQuantity = availableQuantity;
    }
}
