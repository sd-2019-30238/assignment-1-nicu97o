package com.tuturugaNicolae.bestFurnitureDeals.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "furnitureId")
    private Furniture furniture;

    @Column
    private BigDecimal price;

    @Column
    private boolean available;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deal deal = (Deal) o;
        return available == deal.available &&
                Objects.equals(id, deal.id) &&
                Objects.equals(name, deal.name) &&
                dealType == deal.dealType &&
                price.compareTo(deal.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dealType, price, available);
    }
}
