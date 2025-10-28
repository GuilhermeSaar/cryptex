package com.gstech.cryptex.repositories;

import com.gstech.cryptex.model.CryptoPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoPositionRepository extends JpaRepository<CryptoPosition, Long> {

}
