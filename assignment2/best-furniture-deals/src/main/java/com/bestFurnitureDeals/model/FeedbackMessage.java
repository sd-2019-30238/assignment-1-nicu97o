package com.bestFurnitureDeals.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
    @EqualsAndHashCode.Exclude
    private OrderHistory orderHistory;
}
