package com.ivybridge.microservices;

import com.ivybridge.microservices.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangValueRepository extends JpaRepository<ExchangeValue,Long> {

    ExchangeValue findByFromAndTo(String from, String to);
}
