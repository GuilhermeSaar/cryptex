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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_name")
    private String order;

    private LocalDateTime date;
    private BigDecimal amountUsd;
    private BigDecimal priceCrypto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    @Column(precision = 19, scale = 6)
    private BigDecimal amountCrypto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Transaction(String order, Crypto crypto, BigDecimal amountUsd, BigDecimal priceCrypto, User user) {
        this.order = order;
        this.date = LocalDateTime.now();
        this.crypto = crypto;
        this.amountUsd = amountUsd;
        this.priceCrypto = priceCrypto;
        this.amountCrypto = calculateCryptoQuantity();
        this.user = user;
    }
    private BigDecimal calculateCryptoQuantity() {

        return this.amountUsd.divide(this.priceCrypto, 8, RoundingMode.HALF_UP);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Crypto getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    public BigDecimal getAmountUsd() {
        return amountUsd;
    }

    public void setAmountUsd(BigDecimal amountUsd) {
        this.amountUsd = amountUsd;
    }

    public BigDecimal getPriceCrypto() {
        return priceCrypto;
    }

    public void setPriceCrypto(BigDecimal priceCrypto) {
        this.priceCrypto = priceCrypto;
    }

    public BigDecimal getAmountCrypto() {
        return amountCrypto;
    }

    public void setAmountCrypto(BigDecimal amountCrypto) {
        this.amountCrypto = amountCrypto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
