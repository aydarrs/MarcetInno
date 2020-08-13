package ru.innopolis.demo.service;

import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.OrderStatus;
import ru.innopolis.demo.domain.UserType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.innopolis.demo.domain.OrderStatus.*;
import static ru.innopolis.demo.domain.UserType.*;

@Service
public class OrderStatusChangeManager {

    private Map<OrderStatus, List<UserType>> userChangeRulesMapping = new HashMap<>();

    {
        userChangeRulesMapping.put(CREATED, Arrays.asList(ADMIN, SELLER, CUSTOMER));
        userChangeRulesMapping.put(COURIER_APPOINTED, Arrays.asList(ADMIN, COURIER, SELLER));
        userChangeRulesMapping.put(DELIVERING, Arrays.asList(ADMIN, COURIER, SELLER));
        userChangeRulesMapping.put(COMPLETED, Arrays.asList(ADMIN, COURIER, SELLER, CUSTOMER));
    }

    public OrderStatus changeOrderStatus(UserType userType, OrderStatus currentStatus, OrderStatus newStatus) {
        if (userChangeRulesMapping.get(newStatus).contains(userType))
            return newStatus;
        return currentStatus;
    }
}
