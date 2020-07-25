package ru.innopolis.demo.repos;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.demo.domain.Courier;

public interface CourierRepository extends CrudRepository<Courier, Long> {
}
