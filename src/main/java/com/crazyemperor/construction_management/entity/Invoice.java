package com.crazyemperor.construction_management.entity;

import com.crazyemperor.construction_management.entity.auxillirary.InvoiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "Invoices", schema = "public")
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "this string field mustn't be the empty string (i.e. it must have at least one character)")
    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "payee_id", referencedColumnName = "member_id")
    private Member payee;

    @ManyToOne
    @JoinColumn(name = "payer_id", referencedColumnName = "member_id")
    private Member payer;

    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "construction_site_id")
    private ConstructionSite invoice;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", precision = 10)
    @Positive
    private BigDecimal amount;

    @Column(name = "deadline")
    @FutureOrPresent
    private LocalDate deadline;

//    @Enumerated(EnumType.STRING)
    @Column(name = "paid_status")
    private InvoiceStatus paidStatus;

    @AssertFalse
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
