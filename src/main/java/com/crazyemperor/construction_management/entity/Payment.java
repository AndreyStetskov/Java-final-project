package com.crazyemperor.construction_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Payments", schema = "public")
public class Payment {

    @Id
    @Column(name = "payment_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "this string field mustn't be the empty string (i.e. it must have at least one character)")
    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createAt;

    @Basic(optional = false)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @Basic(optional = false)
    @Column(name = "acceptor_id")
    private Long acceptorId;

    @Basic(optional = false)
    @Column(name = "offerer_id")
    private Long offererId;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;
}
