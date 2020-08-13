package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Courier;
import ru.innopolis.demo.domain.DeliveryMethod;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.domain.UserType;
import ru.innopolis.demo.repos.CourierRepository;
import ru.innopolis.demo.repos.UserRepository;

import java.util.Optional;

/**
 * UserService
 *
 * @author Stanislav_Klevtsov
 */
@Service
@Log4j2
public class UserService {

    private UserRepository userRepository;
    private CourierRepository courierRepository;

    @Autowired
    public UserService(UserRepository userRepository, CourierRepository courierRepository) {
        this.userRepository = userRepository;
        this.courierRepository = courierRepository;
    }

    public Iterable<UserAccount> getAllUsers() {
        return userRepository.findAllOrdered();
    }

    public UserAccount getUserById(long userAccountId) {
        Optional<UserAccount> userOptional = userRepository.findById(userAccountId);
        return userOptional.orElse(null);
    }

    public UserAccount getUserByUserName(String userName) {
        Optional<UserAccount> userOptional = userRepository.findUserByUserName(userName);
        return userOptional.orElse(null);
    }

    public void saveNewUser(UserAccount userAccount) {
        userRepository.save(userAccount);
        if (userAccount.getUserType().equals(UserType.COURIER.getRole())) {
            Courier courier = new Courier();
            courier.setUserID(userAccount);
            courier.setDeliveryMethod(DeliveryMethod.CAR);
            courierRepository.save(courier);
        }
    }

    public void deleteUserById(long userAccountId) {
        userRepository.deleteById(userAccountId);
    }

    public void changeUserById(long userAccountId, UserAccount userAccount) {

        UserAccount user = new UserAccount();
        user.setUserId(userAccountId);
        user.setUserType(userAccount.getUserType());
        user.setUserName(userAccount.getUserName());
        user.setFirstName(userAccount.getFirstName());
        user.setLastName(userAccount.getLastName());
        user.setPassword(userAccount.getPassword());

        userRepository.save(user);

        if (null != getUserById(userAccountId)) {
            log.info("User {} changed", userAccountId);
        } else log.error("Error while changing user");
    }
}