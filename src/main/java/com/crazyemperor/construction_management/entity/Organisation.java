package com.crazyemperor.construction_management.entity;

import com.crazyemperor.construction_management.entity.auxillirary.Department;
import com.crazyemperor.construction_management.entity.auxillirary.OrganisationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "organisations", schema = "public")
public class Organisation {

    @Id
    @Column(name = "organisation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "ein")
    private String ein;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createAt;

    @NotBlank
    @Column(name = "department")
    private Department department;

    @NotBlank
    @Column(name = "registration")
    private LocalDate registration;

    @NotBlank(message = "this string field mustn't be the empty string (i.e. it must have at least one character)")
    @Column(name = "location")
    private String location;

    @Column(name = "country")
    private String country;

    @Column(name = "connection")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "+111 (202) 555-0125")
    private String connection;

    @Column(name = "e-mail")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "web_site")
    private String site;

    @Enumerated(EnumType.STRING)
    @Value("${entity.enum.organisation.status-value}")
    @Column(name = "status", columnDefinition = "smallint")
    private OrganisationStatus status;

    @AssertFalse
    @Column(name = "is_deleted")
    private boolean isDeleted;


    @OneToOne(mappedBy = "organisation")
    private Member member;

}
