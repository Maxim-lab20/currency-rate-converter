package pl.cleankod.service;

import pl.cleankod.model.Account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> findAccountById(String id, String currency);

    Optional<Account> findAccountByNumber(String number, String currency);
}
