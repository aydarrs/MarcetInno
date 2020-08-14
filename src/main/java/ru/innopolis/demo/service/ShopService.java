package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.repos.ShopRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        changedShop.setUserId(shop.getUserId());
        changedShop.setImage(shop.getImage());
        shopRepository.save(changedShop);
        if (null != getShopById(shopId)) {
            log.info("User {} changed", shopId);
        } else log.error("Error while changing user");
    }

    public Shop getShopBySellerId(long id) {
        return shopRepository.getByUserId_UserId(id);
    }

    public String copyImage(MultipartFile file, Long shopID) {
        String image = "default-shop.jpg";
        if (file != null) {
            image = shopID + ".jpg";
            try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(
                    new File("target/classes/static/images/shop/" + image)
            ))) {
                byte[] bytes = file.getBytes();
                writer.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }
}
