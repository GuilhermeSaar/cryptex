package com.gstech.cryptex.repositories;

import com.gstech.cryptex.model.Transaction;
import com.gstech.cryptex.projections.TransactionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<TransactionProjection> findByDateBetween(LocalDateTime start, LocalDateTime end);

}
