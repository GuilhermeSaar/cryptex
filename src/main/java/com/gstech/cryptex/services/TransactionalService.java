package com.gstech.cryptex.services;

import com.gstech.cryptex.DTO.TransactionDTO;
import com.gstech.cryptex.model.*;
import com.gstech.cryptex.projections.TransactionProjection;
import com.gstech.cryptex.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionalService {

    @Autowired
    private TransactionRepository transactionalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CryptoRepository cryptoRepository;
    @Autowired
    CryptoPositionService cryptoPositionService;




    // filtrar transações por intervalo
    @Transactional(readOnly = true)
    public List<TransactionProjection> findByDataRange(LocalDate startController, LocalDate endController) {

        LocalDateTime start = startController.atStartOfDay();
        LocalDateTime end = endController.atTime(LocalTime.MAX);

        return transactionalRepository.findByDateBetween(start, end);
    }

    // filtrar transacoes por dia específico
    @Transactional(readOnly = true)
    public List<TransactionProjection> findByDate(LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        return transactionalRepository.findByDateBetween(start, end);
    }

    @Transactional
    public void recordTransaction(Long id, TransactionDTO data) {

        Optional<User> user = userRepository.findById(id);

        Crypto crypto = cryptoRepository.findById(data.crypto()).orElseThrow(
                () -> new IllegalArgumentException("Crypto with id " + id + " does not exist")
        );

        var newTransaction = new Transaction(
                data.order(),
                crypto,
                data.amountUsd(),
                user.get()
        );

        transactionalRepository.save(newTransaction);
        cryptoPositionService.saveCryptoPosition(newTransaction.getAmountUsd(), newTransaction.getAmountCrypto(), user.get().getWallet(), crypto);
    }
}
