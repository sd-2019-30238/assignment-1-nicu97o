package com.tuturugaNicolae.bestFurnitureDeals.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class BoughtFurniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "furnitureId")
    private Furniture furniture;

    @Column
    private int boughtQuantity;

    @Column
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "orderId")
    private ClientOrder clientOrder;

    public BoughtFurniture(long id, int boughtQuantity, BigDecimal price) {
        this.id = id;
        this.boughtQuantity = boughtQuantity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoughtFurniture that = (BoughtFurniture) o;
        return boughtQuantity == that.boughtQuantity &&
                Objects.equals(id, that.id) &&
                price.compareTo(that.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, boughtQuantity, price);
    }
}
