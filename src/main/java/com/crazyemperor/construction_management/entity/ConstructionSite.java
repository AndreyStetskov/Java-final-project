package com.crazyemperor.construction_management.entity;

import com.crazyemperor.construction_management.entity.auxillirary.ConstructionSiteStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "ConstructionSites", schema = "public")
public class ConstructionSite {

    @Id
    @Column(name = "construction_site_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "this string field mustn't be the empty string (i.e. it must have at least one character)")
    @Column(name = "title")
    private String title;

    @CreationTimestamp
    @Column(name = "create_at")
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "constructor_id", referencedColumnName = "member_id")
    private Member constructor;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "member_id")
    private Member owner;

    @ManyToOne
    @JoinColumn(name = "projector_id", referencedColumnName = "member_id")
    private Member projector;

    @ManyToOne
    @JoinColumn(name = "engineering_id", referencedColumnName = "member_id")
    private Member engineering;

    @NotNull(message = "this field mustn't be null")
    @PositiveOrZero
    @Column(name = "amount", precision = 10)
    private BigDecimal amount;

    @Column(name = "start_building")
    @PastOrPresent
    private LocalDate start;

    @Column(name = "finish_building")
    @FutureOrPresent
    private LocalDate finish;

    @NotNull(message = "this field mustn't be null")
    @Column(name = "construction_site_status")
    private ConstructionSiteStatus status;

    @AssertFalse
    @Column(name = "is_deleted")
    private boolean isDeleted;


    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Set<Invoice> invoice;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private Set<Offer> offer;


}
