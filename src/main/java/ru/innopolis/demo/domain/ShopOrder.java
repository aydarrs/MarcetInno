package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shop_order")
public class ShopOrder {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime date;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_user")
    private ShopUser shopUser;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "courier")
    private Courier courier;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

}
