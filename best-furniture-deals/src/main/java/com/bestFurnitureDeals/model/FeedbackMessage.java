package com.bestFurnitureDeals.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class FeedbackMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String messageBody;

    @OneToOne
    @JoinColumn(name = "orderHistoryId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private OrderHistory orderHistory;
}
