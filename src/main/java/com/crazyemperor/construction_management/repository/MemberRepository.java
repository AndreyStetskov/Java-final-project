package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT org.name " +
            "FROM Member member " +
            "LEFT JOIN Organisation org ON member.id = org.id")
    Member findByOrganisationName(final String name);
    @Query("SELECT member " +
            "FROM Member member " +
            "LEFT JOIN Organisation org ON member.id = org.id " +
            "WHERE org.email LIKE '%@%'")
    List<Member> findMembersWithGmailAddress();

}
