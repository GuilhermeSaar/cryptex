package com.gstech.cryptex.services;

import com.gstech.cryptex.DTO.TransactionDTO;
import com.gstech.cryptex.model.Transaction;
import com.gstech.cryptex.model.User;
import com.gstech.cryptex.repositories.TransactionalRepository;
import com.gstech.cryptex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalService {

    @Autowired
    private TransactionalRepository transactionalRepository;
    @Autowired
    private UserRepository userRepository;

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
