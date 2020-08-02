package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "order_shop")
public class OrderShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @OneToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_count")
    private int countProduct;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Override
    public String toString() {
        return "OrderShop{" +
                "orderId=" + orderId +
                ", orderStatus=" + orderStatus +
                ", date=" + date +
                ", userAccount=" + userAccount +
                ", product=" + product +
                ", countProduct=" + countProduct +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
