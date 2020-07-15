package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    private Shop shop;

    private String article;

    private String name;

    private String description;

    private Double price;

    private Integer count;

    @ManyToOne
    private ShopOrder shopOrder;
}
