package ru.innopolis.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.demo.domain.Shop;

@Repository ("shopDAO")
public interface ShopRepository extends CrudRepository<Shop,Long> {

    Iterable<Shop> findShopByShopShopID(long shopID);

}
