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
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    private ClientOrder clientOrder;

    @Column
    private LocalDateTime orderPlaceDateTime;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @OneToOne(mappedBy = "orderHistory", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private FeedbackMessage feedbackMessage;

    public OrderHistory(long id, LocalDateTime orderPlaceDateTime, OrderState orderState) {
        this.id = id;
        this.orderPlaceDateTime = orderPlaceDateTime;
        this.orderState = orderState;
    }

    public OrderHistory(long id, ClientOrder clientOrder, LocalDateTime orderPlaceDateTime, OrderState orderState) {
        this.id = id;
        this.clientOrder = clientOrder;
        this.orderPlaceDateTime = orderPlaceDateTime;
        this.orderState = orderState;
    }
}
