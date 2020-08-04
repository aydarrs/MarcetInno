package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private long courierId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccount userID;

    @Column(name = "delivery_method")
    private DeliveryMethod deliveryMethod;

    @Override
    public String toString() {
        return "Courier{" +
                "courierId=" + courierId +
                ", user=" + userID +
                ", deliveryMethod=" + deliveryMethod +
                '}';
    }
}
