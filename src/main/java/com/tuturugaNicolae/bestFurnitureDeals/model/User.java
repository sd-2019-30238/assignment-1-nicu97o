package com.tuturugaNicolae.bestFurnitureDeals.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String mail;

    @Column
    private BigDecimal walletBalance;

    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<FeedbackMessage> feedbackMessages;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<ClientOrder> clientOrders;

    public User(Long id, String username, String password, String mail, BigDecimal walletBalance, UserType userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.walletBalance = walletBalance;
        this.userType = userType;
    }

    public User(String username, String password, String mail, BigDecimal walletBalance, UserType userType) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.walletBalance = walletBalance;
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(mail, user.mail) &&
                walletBalance.compareTo(user.walletBalance) == 0 &&
                userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, mail, walletBalance, userType);
    }
}
