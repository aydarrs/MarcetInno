package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long shopID;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccount userId;

    @Column(name = "address")
    private String address;

    @Column(name = "shop_name")
    private String name;

    private String image;

}
