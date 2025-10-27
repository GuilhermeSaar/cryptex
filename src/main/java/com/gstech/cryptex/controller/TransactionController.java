package com.gstech.cryptex.controller;

import com.gstech.cryptex.DTO.TransactionDTO;
import com.gstech.cryptex.projections.TransactionProjection;
import com.gstech.cryptex.services.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionalService service;

    @GetMapping
    public ResponseEntity<List<TransactionProjection>> findByDateRange(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        List<TransactionProjection> transactions = service.findByDataRange(startDate, endDate);

        return ResponseEntity.ok(transactions);
    }

    // encontrar por data especifica
    @GetMapping(value = "/{date}")
    public ResponseEntity<List<TransactionProjection>> findByDate(@PathVariable String date) {

        LocalDate localDateParse = LocalDate.parse(date);

        List<TransactionProjection> transactions = service.findByDate(localDateParse);

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping(value = "/record")
    public ResponseEntity<String> recordTransaction(@RequestBody TransactionDTO data) {

        service.recordTransaction(1L, data);

        return ResponseEntity.ok().body("Transaction record completed");
    }
}
