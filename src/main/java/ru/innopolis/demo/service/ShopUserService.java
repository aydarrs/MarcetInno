package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.repos.ShopRepository;

/**
 * ShopServiceFromUser
 *
 * @author Dmitrii_Blazhko 19-Jul-20
 */
@Service
@Log4j2
public class ShopUserService {
    private ShopRepository shopRepository;

    @Autowired
    public ShopUserService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Iterable<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Iterable<Shop> findShopByShopID(Long shopID) {
        return shopRepository.findShopByShopShopID(shopID);
    }
}
