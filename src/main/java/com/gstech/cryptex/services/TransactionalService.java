package com.gstech.cryptex.services;

import com.gstech.cryptex.DTO.TransactionDTO;
import com.gstech.cryptex.model.Transaction;
import com.gstech.cryptex.model.User;
import com.gstech.cryptex.projections.TransactionProjection;
import com.gstech.cryptex.repositories.TransactionRepository;
import com.gstech.cryptex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TransactionalService {

    @Autowired
    private TransactionRepository transactionalRepository;
    @Autowired
    private UserRepository userRepository;


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

        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User with id " + id + " does not exist")
        );

        var newTransaction = new Transaction(
                data.order(),
                data.crypto(),
                data.amountUsdt(),
                data.priceCrypto(),
                user
        );

        transactionalRepository.save(newTransaction);
    }
}
