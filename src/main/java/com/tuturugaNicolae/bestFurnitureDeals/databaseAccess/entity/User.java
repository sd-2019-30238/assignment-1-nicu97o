package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ClientOrder> clientOrders;

    public User(Long id, String username, String password, String mail, UserType userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.userType = userType;
    }

    public User(String username, String password, String mail, UserType userType) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.userType = userType;
    }
}
