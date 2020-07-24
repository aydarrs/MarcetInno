package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.repos.ShopRepository;

import java.util.Optional;

@Service
@Log4j2
public class ShopServiceManager {

    private ShopRepository shopRepository;

    @Autowired
    public ShopServiceManager(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void saveNewShop(Shop shop) {
        shopRepository.save(shop);
        Optional<Shop> optionalShop = shopRepository.findById(shop.getShopID());
        if (optionalShop.isPresent()) {
            log.info("Shop saved successful id={}", optionalShop.get());
        } else {
            log.error("Error saved shop");
        }
    }

    public void changeShopByShopID(long shopID, Shop shop) {
        Optional<Shop> optionalShop = shopRepository.findById(shopID);
        if (optionalShop.isPresent()) {
            saveNewShop(shop);
        } else {
            log.error("Error change shop");
        }
    }

    public void deleteShopByShopID(long shopID) {
        shopRepository.deleteById(shopID);
        Optional<Shop> optionalShop = shopRepository.findById(shopID);
        if (!optionalShop.isPresent()) {
            log.info("Shop deleted successful id={}", shopID);
        } else {
            log.error("Error delete shop id={}", shopID);
        }
    }
}
