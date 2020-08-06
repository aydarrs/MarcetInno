package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Courier;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.repos.CourierRepository;

import java.util.Iterator;

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

    public Iterable<Courier> getFreeCouriers() {
        Iterable<Courier> couriers = courierRepository.findAll();
        log.info("Found free couriers. Couriers are " + (couriers.iterator().hasNext() ? "more then 0." : "0."));
        return couriers;
    }

    public void saveChanged(Courier courier) {
        courierRepository.save(courier);
        log.info("Saved changes for courier: " + courier);
    }

    public Courier getCourierById(long courier_id) {
        log.info("Found courier by id: " + courier_id);
        return courierRepository.findByCourierId(courier_id);
    }

    public Courier getCourierByUser(UserAccount userAccount) {
        log.info("Found courier by userAccount: " + userAccount);
        return courierRepository.findByUserID(userAccount);
    }

    public void deleteCourier(UserAccount userID) {
        log.info("Delete courier by by id: " + userID);
        Courier courier = courierRepository.findByUserID(userID);
        courierRepository.deleteById(courier.getCourierId());
    }

}
