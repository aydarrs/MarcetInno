package ru.innopolis.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.demo.domain.Shop;

/**
 * ShopRepositoryManger
 *
 * @author Dmitrii_Blazhko 19-Jul-20
 */

@Repository ("shopDAO")
public interface ShopRepositoryDAO extends CrudRepository<Shop,Long> {
    //todo реализовать основные методы работы с магазином

}
