package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "delivery_method")
public class DeliveryMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "method_id")
    private long methodId;

    @Column(name = "method_name")
    private String methodName;

    @OneToOne(mappedBy = "deliveryMethod")
    private ShopOrder shopOrder;

}
