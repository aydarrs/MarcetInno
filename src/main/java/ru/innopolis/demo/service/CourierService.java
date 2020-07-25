package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Courier;
import ru.innopolis.demo.repos.CourierRepository;

@Service
@Log4j2
public class CourierService {

    private CourierRepository courierRepository;

    @Autowired
    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public Iterable<Courier> getAllCouriers() {
        Iterable<Courier> couriers = courierRepository.findAll();
        log.info(String.format("Found and return %s couriers from DB.", courierRepository.count()));
        return couriers;
    }
}
