package com.gstech.cryptex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_cryptoPosition")
@NoArgsConstructor
@AllArgsConstructor
public class CryptoPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 19, scale = 6)
    private BigDecimal quantityCrypto = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    public void addCryptoQuantity(BigDecimal cryptoQuantity) {
        this.quantityCrypto = quantityCrypto.add(cryptoQuantity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantityCrypto() {
        return quantityCrypto;
    }

    public void setQuantityCrypto(BigDecimal quantityCrypto) {
        this.quantityCrypto = quantityCrypto;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Crypto getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }
}
