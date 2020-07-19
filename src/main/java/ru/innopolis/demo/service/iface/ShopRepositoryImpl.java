package ru.innopolis.demo.service.iface;

import ru.innopolis.demo.domain.Shop;

/**
 * ShopRepository
 *
 * @author Dmitrii_Blazhko 19-Jul-20
 */

public interface ShopRepositoryImpl {

    static final String SQL_DELETE_PROFILE = "delete from profiles where id = :id";

    Shop addShop(Shop shop);

    Shop updateShop(Shop shop);

    Iterable<Shop> getShopByShopID(Long shopID);

    Iterable<Shop> getAllShop();

    void deleteShopByShopID(Long shopID);
}
