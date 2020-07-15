package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Courier {

    @Id
    @Column(name = "courier_id")
    private long courierId;

    @OneToOne(mappedBy = "courier")
    private ShopOrder shopOrder;
}
