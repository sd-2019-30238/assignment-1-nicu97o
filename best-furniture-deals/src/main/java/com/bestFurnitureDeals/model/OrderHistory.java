package com.bestFurnitureDeals.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime orderPlaceDateTime;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @OneToOne
    @JoinColumn(name = "clientOrderId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private ClientOrder clientOrder;

    @OneToOne(mappedBy = "orderHistory")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private FeedbackMessage feedbackMessage;
}
