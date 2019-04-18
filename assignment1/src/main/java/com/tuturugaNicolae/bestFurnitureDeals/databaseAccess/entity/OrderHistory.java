package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table
@ToString
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ClientOrder clientOrder;

    @Column
    private LocalDateTime orderPlaceDateTime;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @OneToOne(mappedBy = "orderHistory", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FeedbackMessage feedbackMessage;

    public OrderHistory(Long id, LocalDateTime orderPlaceDateTime, OrderState orderState) {
        this.id = id;
        this.orderPlaceDateTime = orderPlaceDateTime;
        this.orderState = orderState;
    }

    public OrderHistory(Long id, ClientOrder clientOrder, LocalDateTime orderPlaceDateTime, OrderState orderState) {
        this.id = id;
        this.clientOrder = clientOrder;
        this.orderPlaceDateTime = orderPlaceDateTime;
        this.orderState = orderState;
    }
}
