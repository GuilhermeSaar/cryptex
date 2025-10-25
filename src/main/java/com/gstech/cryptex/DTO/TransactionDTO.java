package com.gstech.cryptex.DTO;

import java.math.BigDecimal;

public record TransactionDTO(
        String order,
        String crypto,
        BigDecimal amountUsdt,
        BigDecimal priceCrypto

) {
}
