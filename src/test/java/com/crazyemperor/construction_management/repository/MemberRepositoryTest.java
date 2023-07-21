package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.*;
import com.crazyemperor.construction_management.entity.auxillirary.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findByOrganisationName_ReturnMemberByName_Success() {

//        given
        Member expected = new Member();
        Member member = new Member();
        Organisation organisation = new Organisation();
        Organisation organisationTwo = new Organisation();
        Set<MemberType> type = new HashSet<>();

        organisation.setEin("46-4545464");
        organisation.setName("Pupkin and Ko");
        organisation.setDepartment(Department.ENERGY);
        organisation.setRegistration(LocalDate.of(1895,1,30));
        organisation.setLocation("Address");
        organisation.setStatus(OrganisationStatus.ACTIVE);
        expected.setOrganisation(organisation);
        expected.getOrganisation().getName();
        expected.setType(type);
        expected.getType().add(MemberType.CONSTRUCTOR);
        expected.setStatus(MemberStatus.ACTIVE);

        organisationTwo.setEin("14-3182558");
        organisationTwo.setName("National School");
        organisationTwo.setDepartment(Department.ENERGY);
        organisationTwo.setRegistration(LocalDate.of(2000,10,30));
        organisationTwo.setLocation("Some address");
        organisationTwo.setStatus(OrganisationStatus.ACTIVE);
        member.setOrganisation(organisationTwo);
        member.getOrganisation().getName();
        member.setType(type);
        member.getType().add(MemberType.PROJECTOR);
        member.setStatus(MemberStatus.ACTIVE);

        memberRepository.save(expected);
        memberRepository.save(member);

//        when
        Member actual = memberRepository.findByOrganisationName("Pupkin and Ko");

//        then
        assertEquals(expected, actual);
    }

    @Test
    void findMembersWithGmailAddress_ReturnMemberWithEmail_Success() {

//        given
        Member member = new Member();
        Member memberTwo = new Member();
        Organisation organisation = new Organisation();
        Organisation organisationTwo = new Organisation();
        Set<MemberType> type = new HashSet<>();
        int expected = 2;

        organisation.setEin("46-4545464");
        organisation.setName("Pupkin and Ko");
        organisation.setDepartment(Department.ENERGY);
        organisation.setRegistration(LocalDate.of(1895,1,30));
        organisation.setLocation("Address");
        organisation.setStatus(OrganisationStatus.ACTIVE);
        member.setOrganisation(organisation);
        member.getOrganisation().getName();
        member.getOrganisation().setEmail("java@gmail.com");
        member.setType(type);
        member.getType().add(MemberType.CONSTRUCTOR);
        member.setStatus(MemberStatus.ACTIVE);

        organisationTwo.setEin("14-3182558");
        organisationTwo.setName("Some Name");
        organisationTwo.setDepartment(Department.ENERGY);
        organisationTwo.setRegistration(LocalDate.of(2000,10,30));
        organisationTwo.setLocation("Some address");
        organisationTwo.setStatus(OrganisationStatus.ACTIVE);
        memberTwo.setOrganisation(organisationTwo);
        memberTwo.getOrganisation().getName();
        memberTwo.getOrganisation().setEmail("unit@gmail.com");
        memberTwo.setType(type);
        memberTwo.getType().add(MemberType.PROJECTOR);
        memberTwo.setStatus(MemberStatus.ACTIVE);

        memberRepository.save(member);
        memberRepository.save(memberTwo);

//        when
        List<Member> actual = memberRepository.findMembersWithGmailAddress();

//        then
        assertEquals(expected, actual.size());
    }
}