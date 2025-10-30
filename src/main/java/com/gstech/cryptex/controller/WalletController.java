package com.gstech.cryptex.controller;

import com.gstech.cryptex.model.User;
import com.gstech.cryptex.services.UserService;
import com.gstech.cryptex.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;


    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateWallet(@PathVariable Long id) {

        User user = userService.findById(id);
        walletService.updateWallet(user.getWallet().getId());

        return ResponseEntity.ok().body("Carteira atualizada");
    }
}
