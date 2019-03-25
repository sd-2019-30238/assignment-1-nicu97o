package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table
public class FeedbackMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String messageBody;

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "orderHistoryId")
    @EqualsAndHashCode.Exclude
    private OrderHistory orderHistory;

    public FeedbackMessage(long id, String title, String messageBody) {
        this.id = id;
        this.title = title;
        this.messageBody = messageBody;
    }
}
