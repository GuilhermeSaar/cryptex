package com.gstech.cryptex.controller;

import com.gstech.cryptex.DTO.TransactionDTO;
import com.gstech.cryptex.services.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionalService service;

    @PostMapping(value = "/record")
    public ResponseEntity<String> recordTransaction(@RequestBody TransactionDTO data) {

        service.recordTransaction(1L, data);

        return ResponseEntity.ok().body("Transaction record completed");
    }
}
