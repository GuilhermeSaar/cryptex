package com.gstech.cryptex.services;

import com.gstech.cryptex.model.CryptoPosition;
import com.gstech.cryptex.model.Wallet;
import com.gstech.cryptex.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {

    @Autowired
    private WalletRepository repository;

    @Transactional
    public void updateWallet(Long walletId) {

        BigDecimal total = repository.calculateWalletValue(walletId);
        Wallet wallet = repository.findById(walletId).orElseThrow(
                () -> new IllegalArgumentException("Wallet not found: " + walletId)
        );

        wallet.setPositionUsd(total);
        repository.save(wallet);
    }
}
