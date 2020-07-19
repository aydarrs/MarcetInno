package ru.innopolis.demo.service.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.repos.ShopRepositoryDAO;
import ru.innopolis.demo.service.iface.ShopRepositoryImpl;

/**
 * ShopServiceManager
 *
 * @author Dmitrii_Blazhko 19-Jul-20
 */

@Service ("shopServiceManger")
@Log4j2
public class ShopServiceManager implements ShopRepositoryImpl {


    private ShopRepositoryDAO shopRepositoryDAO;

    @Autowired
    public ShopServiceManager(ShopRepositoryDAO shopRepositoryDAO) {
        this.shopRepositoryDAO = shopRepositoryDAO;
    }

    @Override
    public Shop addShop(Shop shop) {
        shop = shopRepositoryDAO.save(shop);
        return shop;
    }

    @Override
    public Shop updateShop(Shop shop) {
        shop = shopRepositoryDAO.save(shop);
        return shop;
    }

    @Override
    public Iterable<Shop> getAllShop() {
        return shopRepositoryDAO.findAll();
    }

    @Override
    public void deleteShopByShopID(Long shopID) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("shopID", shopID);
//        jdbcTemplate.update(SQL_DELETE_PROFILE, params);

    }


    @Override
    public Iterable<Shop> getShopByShopID(Long shopID) {
        return shopRepositoryDAO.findAll();
    }

}
