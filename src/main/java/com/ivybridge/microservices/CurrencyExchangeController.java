package com.ivybridge.microservices;

import com.ivybridge.microservices.model.ExchangeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment; // Spring provided configuration

    @Autowired
    private ExchangValueRepository repository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        exchangeValue.setPort(
                Integer.parseInt(environment.getProperty("local.server.port")));
        logger.info("{}",exchangeValue);
        return exchangeValue;
    }

    @GetMapping("/currency-exchange-values")
    public List<ExchangeValue> getAllExchangeValues() {
        return repository.findAll();
    }

}
