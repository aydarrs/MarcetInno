package ru.innopolis.demo.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.domain.UserAccount;

@Repository
@EnableTransactionManagement
@Transactional
public interface ShopRepository extends CrudRepository<Shop, Long> {

    @Query("select s from Shop s order by s.shopID")
    Iterable<Shop> findAllOrdered();
}
