package pl.cleankod.service.impl;

import org.springframework.stereotype.Service;
import pl.cleankod.model.Account;
import pl.cleankod.service.AccountService;
import pl.cleankod.service.FindAccountAndConvertCurrencyUseCase;
import pl.cleankod.service.FindAccountUseCase;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Currency;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final FindAccountAndConvertCurrencyUseCase findAccountAndConvertCurrencyUseCase;
    private final FindAccountUseCase findAccountUseCase;

    public AccountServiceImpl(FindAccountAndConvertCurrencyUseCase findAccountAndConvertCurrencyUseCase, FindAccountUseCase findAccountUseCase) {
        this.findAccountAndConvertCurrencyUseCase = findAccountAndConvertCurrencyUseCase;
        this.findAccountUseCase = findAccountUseCase;
    }

    @Override
    public Optional<Account> findAccountById(String id, String currency) {
        return Optional.ofNullable(currency)
                .map(s -> findAccountAndConvertCurrencyUseCase.execute(Account.Id.of(id), Currency.getInstance(s)))
                .orElseGet(() -> findAccountUseCase.execute(Account.Id.of(id)));
    }

    @Override
    public Optional<Account> findAccountByNumber(String number, String currency) {
        Account.Number accountNumber = Account.Number.of(URLDecoder.decode(number, StandardCharsets.UTF_8));
        return Optional.ofNullable(currency)
                .map(s -> findAccountAndConvertCurrencyUseCase.execute(accountNumber, Currency.getInstance(s)))
                .orElseGet(() -> findAccountUseCase.execute(accountNumber));
    }
}
