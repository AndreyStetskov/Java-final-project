package com.crazyemperor.construction_management.entity;

import com.crazyemperor.construction_management.entity.auxillirary.MemberStatus;
import com.crazyemperor.construction_management.entity.auxillirary.MemberType;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "members", schema = "public")
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organisation_id", referencedColumnName = "organisation_id")
    private Organisation organisation;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "smallint")
    private Set<MemberType> type;

    @PositiveOrZero
    @Column(name = "budget", precision = 10)
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    @Value("${entity.enum.member.status-value}")
    @Column(name = "status", columnDefinition = "smallint")
    private MemberStatus status;

    @AssertFalse
    @Column(name = "is_deleted")
    private boolean isDeleted;


    @OneToMany(mappedBy = "constructor", cascade = CascadeType.ALL)
    private Set<ConstructionSite> constructor;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<ConstructionSite> owner;

    @OneToMany(mappedBy = "projector", cascade = CascadeType.ALL)
    private Set<ConstructionSite> projector;

    @OneToMany(mappedBy = "engineering", cascade = CascadeType.ALL)
    private Set<ConstructionSite> engineering;

    @OneToMany(mappedBy = "payee", cascade = CascadeType.ALL)
    private Set<Invoice> payee;

    @OneToMany(mappedBy = "payer", cascade = CascadeType.ALL)
    private Set<Invoice> payer;

    @OneToMany(mappedBy = "acceptor", cascade = CascadeType.ALL)
    private Set<Offer> acceptor;

    @OneToMany(mappedBy = "offerer", cascade = CascadeType.ALL)
    private Set<Offer> offerer;
}
