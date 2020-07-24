package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.repos.ShopRepository;

@Service
@Log4j2
public class ShopService {

    private ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Iterable<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Iterable<Shop> findShopByShopID(Long shopID) {
        return shopRepository.findShopByShopID(shopID);
    }
}
