package ru.innopolis.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "shop_user")
public class ShopUser {

    @Id
    @Column(name = "user_id")
    private long userId;

    @OneToOne(mappedBy = "shopUser")
    private ShopOrder shopOrder;

}
