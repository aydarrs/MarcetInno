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

    @Column(name = "address_id")
    private String addressID;

    @Column(name = "name")
    private String name;

    @OneToMany
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ShopOrder> orders;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ShopUser> users;

}
