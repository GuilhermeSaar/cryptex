package com.gstech.cryptex.repositories;

import com.gstech.cryptex.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query(value = """
    SELECT SUM(c.price_usd * cp.quantity_crypto) 
    FROM tb_crypto_position cp
    JOIN tb_crypto c ON c.id = cp.crypto_id
    WHERE cp.wallet_id = :walletId
""", nativeQuery = true)
    BigDecimal calculateWalletValue(@Param("walletId") Long walletId);

}
