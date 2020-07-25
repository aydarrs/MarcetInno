package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Courier {
    //TODO: Нужны комментарии
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private long courierId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "delivery_method")
    private DeliveryMethod deliveryMethod;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private OrderShop orderShop;

    public Long getOrderShop() {
        if (orderShop == null)
            return null;
        return orderShop.getOrderId();
    }
}
