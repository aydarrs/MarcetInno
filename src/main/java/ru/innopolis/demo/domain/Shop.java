package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Shop {

    //TODO: Нужны комментарии
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long shopID;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @OneToMany
    private List<Product> products;

}
