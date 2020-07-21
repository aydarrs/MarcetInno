package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.repos.ShopRepository;
import ru.innopolis.demo.repos.ShopRepositoryImpl;

@Service("shopServiceFromManager")
@Log4j2
public class ShopServiceManager implements ShopRepositoryImpl {

    private ShopRepository shopRepository;

    @Autowired
    public ShopServiceManager(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    /**
     * просмотр всех магазинов
     *
     * @param
     * @return
     */
    @Override
    public Iterable<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    /**
     * добавить магазин
     *
     * @param shop
     */
    @Override
    public void addNewShop(Shop shop) {
        shopRepository.save(shop);
    }

    /**
     * изменить магазин по ID
     *
     * @param shopID
     * @param shop
     */
    public void changeShopByShopID(long shopID, Shop shop) {
        Iterable<Shop> change = getShopByShopId(shopID);
        //внести изменения change из shop
        addNewShop(shop);
    }

    /**
     * просмотр одного магазина
     *
     * @param shopID
     * @return
     */
    @Override
    public Iterable<Shop> getShopByShopId(long shopID) {
        return shopRepository.findShopByShopShopID(shopID);
    }

    /**
     * удалить магазин
     *
     * @param shopID
     */
    @Override
    public void deleteShopByShopID(Long shopID) {
        shopRepository.deleteById(shopID);
    }
}
