package com.gstech.cryptex.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table (name = "tb_transaction")
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_name")
    private String order;

    private String crypto;
    private BigDecimal amountUsdt;
    private BigDecimal priceCrypto;
    private BigDecimal amountCrypto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
