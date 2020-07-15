package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderStatus {

    @Id
    @Column(name = "status_id")
    private long statusId;

    private String status;

    @OneToOne(mappedBy = "orderStatus")
    private ShopOrder shopOrder;
}
