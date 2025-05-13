package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidAccountException;
import com.example.exception.InvalidLoginException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account register(Account account){
        if (account.getUsername() == null || account.getUsername() .trim().isEmpty()){
            throw new InvalidAccountException();
        }
        if (account.getPassword() == null || account.getPassword().length() < 4){
            throw new InvalidAccountException();
        }
        if (accountRepository.existsByUsername(account.getUsername())) {
            throw new DuplicateUsernameException();
        }

        return accountRepository.save(account);
    }

    public Account login(Account account){
        if (accountRepository.existsByUsername(account.getUsername())){
            Account existigAccount = accountRepository.findByUsername(account.getUsername());
            System.out.println(existigAccount.toString());
            System.out.println(account.toString());

            System.out.println(existigAccount.getPassword());
            System.out.println(account.getPassword());
            if (account.getPassword().equals(existigAccount.getPassword())){
                System.out.println("Matched");
                return existigAccount;
            }
        }
        throw new InvalidLoginException();
    }
}
