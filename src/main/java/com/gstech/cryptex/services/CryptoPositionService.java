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
    public void saveCryptoPosition(Long id, BigDecimal amount, Wallet wallet, Crypto crypto) {

        var position = repository.findById(id).orElse(
                new CryptoPosition()
        );

        position.addCryptoQuantity(amount);
        position.setCrypto(crypto);
        position.setWallet(wallet);
        repository.save(position);

    }
}
