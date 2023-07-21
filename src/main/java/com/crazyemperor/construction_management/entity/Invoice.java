package com.crazyemperor.construction_management.entity;

import com.crazyemperor.construction_management.entity.auxillirary.InvoiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;


@Data
@Entity
@Table(name = "invoices", schema = "public")
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "title of invoice mustn't be the empty string (i.e. it must have at least one character)")
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

    @NotNull(message = "amount mustn't be null")
    @Column(name = "amount", precision = 10)
    @PositiveOrZero
    private BigDecimal amount;

    @NotNull(message = "Be sure to include a deadline")
    @Column(name = "deadline")
    @FutureOrPresent
    private LocalDate deadline;

    @Enumerated(EnumType.ORDINAL)
    @Value("${entity.enum.invoice.status-value}")
    @Column(name = "paid_status", columnDefinition = "smallint")
    private InvoiceStatus paidStatus;

    @AssertFalse
    @Column(name = "is_deleted")
    private boolean isDeleted;


    @OneToMany(mappedBy = "paid", cascade = CascadeType.ALL)
    private Set<Payment> paid;
}
