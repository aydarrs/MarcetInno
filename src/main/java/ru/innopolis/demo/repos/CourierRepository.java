package ru.innopolis.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.demo.domain.Courier;
import ru.innopolis.demo.domain.UserAccount;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Long> {

    Courier findByCourierId(Long courierId);

    Courier findByUserID(UserAccount userID);

    void deleteById(Long courierID);

}
