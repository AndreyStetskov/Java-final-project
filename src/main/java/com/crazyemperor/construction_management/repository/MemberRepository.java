package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByOrganisationId(final long id);
    Member findById(final long id);
    @Query("SELECT member " +
            "FROM Member member " +
            "LEFT JOIN Organisation org ON member.id = org.id " +
            "WHERE org.email LIKE '%@gmail.com'")
    List<Member> findMembersWithGmailAddress();

    @Query("SELECT org.name, building.title " +
            "FROM Payment pay " +
            "LEFT JOIN Invoice bill ON pay.id = bill.id " +
            "LEFT JOIN ConstructionSite building ON bill.id = building.id " +
            "LEFT JOIN Member member ON building.id = member.id " +
            "LEFT JOIN Organisation org ON member.id = org.id")
    List<Member> findAllPaidMembers();
}
