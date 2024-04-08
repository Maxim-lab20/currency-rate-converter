package pl.cleankod.service.impl;

import com.example.accounts.api.model.AccountDto;
import com.example.accounts.api.model.Id;
import com.example.accounts.api.model.Money;
import com.example.accounts.api.model.Number;
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
    public Optional<AccountDto> findAccountById(String id, String currency) {
        Optional<Account> accountOptional = Optional.ofNullable(currency)
                .map(s -> findAccountAndConvertCurrencyUseCase.execute(Account.Id.of(id), Currency.getInstance(s)))
                .orElseGet(() -> findAccountUseCase.execute(Account.Id.of(id)));

        return accountOptional.map(this::mapToGeneratedAccount);
    }

    @Override
    public Optional<AccountDto> findAccountByNumber(String number, String currency) {
        Account.Number accountNumber = Account.Number.of(URLDecoder.decode(number, StandardCharsets.UTF_8));
        Optional<Account> accountOptional = Optional.ofNullable(currency)
                .map(s -> findAccountAndConvertCurrencyUseCase.execute(accountNumber, Currency.getInstance(s)))
                .orElseGet(() -> findAccountUseCase.execute(accountNumber));

        return accountOptional.map(this::mapToGeneratedAccount);
    }

    private AccountDto mapToGeneratedAccount(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(new Id().value(account.id().value()));
        accountDto.setBalance(new Money()
                .amount(account.balance().amount())
                .currency(account.balance().currency().toString()));
        accountDto.setNumber(new Number().value(account.number().value()));
        return accountDto;
    }

}
