package ru.innopolis.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.demo.domain.UserAccount;

import java.util.Optional;

/**
 * UserRepository
 *
 * @author Stanislav_Klevtsov
 */
@Repository
@EnableTransactionManagement
@Transactional
public interface UserRepository extends CrudRepository<UserAccount, Long> {

    Optional<UserAccount> findUserByUserName(String userName);

}