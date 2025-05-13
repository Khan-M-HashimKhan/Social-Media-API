package com.example.repository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import com.example.entity.Account;

public interface AccountRepository extends JpaRepositoryImplementation<Account,Long > {

    boolean existsByUsername(String username);
    boolean existsByAccountId(Integer id);
    Account findByUsername(String username);
}
