package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.repos.ShopRepository;

import java.util.Optional;

@Service
@Log4j2
public class ShopService {

    private ShopRepository shopRepository;
    private OrderService orderService;

    @Autowired
    public ShopService(ShopRepository shopRepository, OrderService orderService) {
        this.shopRepository = shopRepository;
        this.orderService = orderService;
    }

    public Iterable<Shop> getAllShops() {
        return shopRepository.findAllOrdered();
    }

    public Shop getShopById(long shopId) {
        Optional<Shop> shopOptional = shopRepository.findById(shopId);
        return shopOptional.orElse(null);
    }

    public void saveNewShop(Shop shop) {
        shopRepository.save(shop);
    }

    /*public void deleteShopById(long shopId) {
        shopRepository.deleteById(shopId);
    }*/

    public void changeShopById(long shopId, Shop shop) {

        Shop changedShop = new Shop();
        changedShop.setShopID(shopId);
        changedShop.setAddress(shop.getAddress());
        changedShop.setName(shop.getName());
        shopRepository.save(changedShop);
        if (null != getShopById(shopId)) {
            log.info("User {} changed", shopId);
        } else log.error("Error while changing user");
    }

    public Shop getShopBySellerId(long id) {
        return shopRepository.getByUserId_UserId(id);
    }

}
