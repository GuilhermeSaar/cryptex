package com.gstech.cryptex.services;

import com.gstech.cryptex.model.Crypto;
import com.gstech.cryptex.model.CryptoPosition;
import com.gstech.cryptex.model.Wallet;
import com.gstech.cryptex.repositories.CryptoPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CryptoPositionService {

    @Autowired
    private CryptoPositionRepository repository;

    @Transactional
    public void saveCryptoPosition(BigDecimal amountUsdt, BigDecimal quantityCrypto, Wallet wallet, Crypto crypto) {

        var position = repository.findByWalletAndCrypto(wallet, crypto)
                .map(x -> {
                    x.addCryptoQuantity(quantityCrypto);
                    x.sumTotalInvested(amountUsdt);
                    return x;
                })
                .orElseGet(() -> {
                    return new CryptoPosition(amountUsdt, quantityCrypto, wallet, crypto);
                });

        repository.save(position);
    }
}
