package ru.innopolis.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.demo.domain.Courier;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Long> {

    Iterable<Courier> findByOrderShopIsNull();

    Courier findByCourierId(Long courierId);

}
