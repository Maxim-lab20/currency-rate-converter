package pl.cleankod.service;

import com.example.accounts.api.model.AccountDto;

import java.util.Optional;

public interface AccountService {

    Optional<AccountDto> findAccountById(String id, String currency);

    Optional<AccountDto> findAccountByNumber(String number, String currency);
}
