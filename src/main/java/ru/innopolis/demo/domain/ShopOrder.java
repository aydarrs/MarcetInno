package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shop_order")
public class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime date;

    @ManyToOne
    @JoinColumn(name = "courier")
    private Courier courier;

    @OneToMany
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "shop_user")
    private ShopUser shopUser;

    @ManyToOne
    @JoinColumn(name = "shop")
    private Shop shop;

    @OneToOne
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @OneToOne
    @JoinColumn(name = "delivery_method")
    private DeliveryMethod deliveryMethod;
}
