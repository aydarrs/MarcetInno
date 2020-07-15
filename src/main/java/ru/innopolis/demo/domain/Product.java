package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Product {


    @Id
    @Column(name = "product_id")
    private long productId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private ShopOrder shopOrder;
}
