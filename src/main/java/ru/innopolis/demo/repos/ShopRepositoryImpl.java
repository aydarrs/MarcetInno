package ru.innopolis.demo.repos;

import ru.innopolis.demo.domain.Shop;

/**
 * ShopRepository
 *
 * @author Dmitrii_Blazhko 19-Jul-20
 */

public interface ShopRepositoryImpl {

    Iterable<Shop> getAllShops();

    Iterable<Shop> getShopByShopId(long shopID);

    void addNewShop(Shop shop);

    void changeShopByShopID(long shopID, Shop shop);

    void deleteShopByShopID(Long shopID);
}


