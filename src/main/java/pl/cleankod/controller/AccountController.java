package pl.cleankod.controller;

import com.example.accounts.api.AccountApi;
import com.example.accounts.api.model.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.cleankod.service.AccountService;

@RestController
public class AccountController implements AccountApi {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public ResponseEntity<AccountDto> findAccountById(String id, String currency) {
        return accountService.findAccountById(id, currency)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<AccountDto> findAccountByNumber(String number, String currency) {
        return accountService.findAccountByNumber(number, currency)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
