package com.tuturugaNicolae.bestFurnitureDeals.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean approved;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "clientOrder", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<BoughtFurniture> boughtFurniture;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "userId")
    private User client;

    @OneToOne(mappedBy = "clientOrder", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private OrderHistory orderHistory;

    public ClientOrder(long id, boolean approved, PaymentMethod paymentMethod, BigDecimal totalPrice) {
        this.id = id;
        this.approved = approved;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOrder that = (ClientOrder) o;
        return approved == that.approved &&
                Objects.equals(id, that.id) &&
                paymentMethod == that.paymentMethod &&
                totalPrice.compareTo(that.totalPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, approved, paymentMethod, totalPrice);
    }
}
