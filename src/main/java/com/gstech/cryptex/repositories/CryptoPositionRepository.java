package com.gstech.cryptex.repositories;

import com.gstech.cryptex.model.Crypto;
import com.gstech.cryptex.model.CryptoPosition;
import com.gstech.cryptex.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoPositionRepository extends JpaRepository<CryptoPosition, Long> {

    Optional<CryptoPosition> findByWalletAndCrypto(Wallet wallet, Crypto crypto);

}
