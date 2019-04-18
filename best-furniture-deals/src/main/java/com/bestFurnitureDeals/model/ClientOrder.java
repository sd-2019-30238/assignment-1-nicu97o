package com.bestFurnitureDeals.model;

import com.bestFurnitureDeals.model.observer.Observable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class ClientOrder extends Observable<UserObserver> {
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

    @Column
    private boolean finished;

    @OneToOne(mappedBy = "clientOrder")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private OrderHistory orderHistory;

    @OneToMany(mappedBy = "clientOrder", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private User user;

    public ClientOrder(boolean approved, PaymentMethod paymentMethod, BigDecimal totalPrice, boolean finished) {
        this.approved = approved;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.finished = finished;
    }

    public ClientOrder(long id, boolean approved, PaymentMethod paymentMethod, BigDecimal totalPrice, boolean finished) {
        this.id = id;
        this.approved = approved;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.finished = finished;
    }
}
