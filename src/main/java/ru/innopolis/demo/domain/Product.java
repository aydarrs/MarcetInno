package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Product {
    //TODO: Нужны комментарии
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "article")
    private String article;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "count")
    private Integer count;

}
