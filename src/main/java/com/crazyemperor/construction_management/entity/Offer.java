package com.crazyemperor.construction_management.entity;

import com.crazyemperor.construction_management.entity.auxillirary.OfferStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Offers", schema = "public")
public class Offer {

    @Id
    @Column(name = "offer_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "this string field mustn't be the empty string (i.e. it must have at least one character)")
    @Column(name = "title")
    private String title;

    @Column(name = "create_at")
    @CreationTimestamp
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "acceptor_id", referencedColumnName = "member_id")
    private Member acceptor;

    @ManyToOne
    @JoinColumn(name = "offerer_id", referencedColumnName = "member_id")
    private Member offerer;

    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "construction_site_id")
    private ConstructionSite offer;

    @Column(name = "description")
    private String description;

    @Positive
    @Column(name = "amount")
    private BigDecimal amount;

    @FutureOrPresent
    @Column(name = "start")
    private LocalDate start;

    @FutureOrPresent
    @Column(name = "deadline")
    private LocalDate deadline;

    @FutureOrPresent
    @Column(name = "duration")
    private LocalDateTime duration;

    @Column(name = "status")
    private OfferStatus status;

    @AssertFalse
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
