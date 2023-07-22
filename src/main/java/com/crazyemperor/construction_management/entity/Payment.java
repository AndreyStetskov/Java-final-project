package com.crazyemperor.construction_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "payments", schema = "public")
public class Payment {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "title of payment mustn't be the empty string (i.e. it must have at least one character)")
    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "paid_invoice_id", referencedColumnName = "invoice_id")
    private Invoice paid;

    @Column(name = "description")
    private String description;
}
