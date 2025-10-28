package com.gstech.cryptex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_wallet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalInvested;
    private BigDecimal totalSales;
    private BigDecimal TotalValue;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private Set<CryptoPosition> cryptos = new HashSet<>();


    public boolean containsCrypto(Crypto crypto) {

        for (CryptoPosition c : cryptos) {
            if (c.getCrypto().getName().equals(crypto.getName())) {
                return true;
            }
        }
        return false;
    }

    public Long idCrypto(Crypto crypto) {
        for (CryptoPosition c : cryptos) {
            if (c.getCrypto().equals(crypto)) {
                return c.getId();
            }
        }
        return null;
    }

}
