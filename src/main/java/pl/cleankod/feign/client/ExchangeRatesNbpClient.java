package pl.cleankod.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.cleankod.feign.config.ExchangeRatesNbpClientConfiguration;
import pl.cleankod.model.RateWrapper;

@FeignClient(
        name = "exchangeRatesNbpClient",
        url = "${provider.nbp-api.base-url}",
        configuration = ExchangeRatesNbpClientConfiguration.class
)
public interface ExchangeRatesNbpClient {

    @GetMapping("/exchangerates/rates/{table}/{currency}/2022-02-08")
    RateWrapper fetch(@PathVariable("table") String table, @PathVariable("currency") String currency);

}
