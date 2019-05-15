package com.bestfurnituredeals.assignment3.model.database;

import com.bestfurnituredeals.assignment3.decorator.IProduct;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product implements IProduct {
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Deal deal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientOrderId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ClientOrder clientOrder;

    public Product(int selectedQuantity, Deal deal, ClientOrder clientOrder) {
        this.selectedQuantity = selectedQuantity;
        this.deal = deal;
        this.clientOrder = clientOrder;
    }

    @Override
    public void decorate() {
        deal.setAvailableQuantity(deal.getAvailableQuantity() - getSelectedQuantity());
        setPrice(deal.getPrice().multiply(BigDecimal.valueOf(getSelectedQuantity())));
    }

    @Override
    public IProduct getProduct() {
        return this;
    }
}