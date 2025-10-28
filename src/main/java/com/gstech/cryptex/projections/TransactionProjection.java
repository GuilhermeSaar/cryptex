package com.gstech.cryptex.projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionProjection {

    LocalDateTime getDate();
    String getOrder();
    String getCrypto();
    BigDecimal getAmountUsd();
    BigDecimal getPriceCrypto();
    BigDecimal getAmountCrypto();
}
