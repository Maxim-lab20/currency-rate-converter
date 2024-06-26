package pl.cleankod.service.impl;

import org.springframework.stereotype.Service;
import pl.cleankod.model.Money;
import pl.cleankod.service.CurrencyConversionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Service
public class CurrencyConversionStubService implements CurrencyConversionService {
    private static final BigDecimal PLN_TO_EUR_RATE = BigDecimal.valueOf(0.22d);
    private static final BigDecimal EUR_TO_PLN_RATE = BigDecimal.valueOf(4.58d);

    @Override
    public Money convert(Money money, Currency targetCurrency) {
        return money.currency().equals(targetCurrency)
                ? money
                : calculate(money, targetCurrency);
    }

    private Money calculate(Money money, Currency targetCurrency) {
        BigDecimal rate = "PLN".equals(targetCurrency.getCurrencyCode()) ? EUR_TO_PLN_RATE : PLN_TO_EUR_RATE;
        return Money.of(money.amount().multiply(rate).setScale(2, RoundingMode.HALF_UP), targetCurrency);
    }
}
