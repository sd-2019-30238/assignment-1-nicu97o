package com.bestFurnitureDeals.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int selectedQuantity;

    @Column
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Deal deal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientOrderId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private ClientOrder clientOrder;

    public Product(int selectedQuantity, BigDecimal price, Deal deal, ClientOrder clientOrder) {
        this.selectedQuantity = selectedQuantity;
        this.price = price;
        this.deal = deal;
        this.clientOrder = clientOrder;
    }
}
