package com.gstech.cryptex.DTO;

import java.math.BigDecimal;

public record TransactionDTO(
        String order,
        Long crypto,
        BigDecimal amountUsd
) {
}
