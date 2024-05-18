package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_BILL_DETAIL")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BILL_DETAIL_ID_SEQ")
    @SequenceGenerator(name = "BILL_DETAIL_ID_SEQ", sequenceName = "BILL_DETAIL_ID_SEQ", allocationSize = 1)
    private Long id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "BILL_ID", nullable = false)
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;
}
