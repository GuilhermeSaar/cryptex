package com.gstech.cryptex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity
@Table (name = "tb_transaction")
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_name")
    private String order;

    private LocalDateTime date;
    private String crypto;
    private BigDecimal amountUsdt;
    private BigDecimal priceCrypto;

    @Column(precision = 19, scale = 6)
    private BigDecimal amountCrypto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Transaction(String order, String crypto, BigDecimal amountUsdt, BigDecimal priceCrypto, User user) {
        this.order = order;
        this.date = LocalDateTime.now();
        this.crypto = crypto;
        this.amountUsdt = amountUsdt;
        this.priceCrypto = priceCrypto;
        this.amountCrypto = calculateCryptoQuantity();
        this.user = user;
    }


    private BigDecimal calculateCryptoQuantity() {

        return this.amountUsdt.divide(this.priceCrypto, 8, RoundingMode.HALF_UP);
    }
}
