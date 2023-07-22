package com.crazyemperor.construction_management.database.members.implementation;

import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.entity.Organisation;
import com.crazyemperor.construction_management.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class MemberDataBaseImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberDataBaseImpl memberDB;


    @Test
    void addMember_AddNewInvoice_Success() {

//        given
        Member expected = new Member();

//        when
        memberDB.addMember(expected);

//        then
        verify(memberRepository, times(1)).save(expected);
    }

    @Test
    void getMembers_ReturnListOfAllMembersOfThreeObjects_Success() {

//        given
        List<Member> expected = new ArrayList<>();
        expected.add(new Member());
        expected.add(new Member());
        expected.add(new Member());

        when(memberRepository.findAll()).thenReturn(expected);

//        when
        List<Member> actual = memberDB.getMembers();

//        then
        assertEquals(expected, actual);
        verify(memberRepository, times(1)).findAll();
    }

    @Test
    void getByID_ReturnInvoiceById_Success() {

//        given
        Member expected = new Member();

        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(expected));

//        when
        Optional<Member> actualOptional = memberRepository.findById(anyLong());
        Member actual = actualOptional.get();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByOrganisationName_ChangeDeleteStatusToTrue_Success() {

//        given
        String name = anyString();

        Optional<Member> optional = Optional.ofNullable(new Member());
        Member actual = optional.get();
        actual.setOrganisation(new Organisation());
        actual.getOrganisation().setName(name);
        actual.setDeleted(false);

        Member expected = new Member();
        expected.setOrganisation(new Organisation());
        expected.getOrganisation().setName(name);
        expected.setDeleted(true);

        when(memberRepository.findByOrganisationName(name)).thenReturn(actual);

//        when
        memberDB.deleteByOrganisationName(actual.getOrganisation().getName());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID_ChangeDeleteStatusToTrue_Success() {

//        given
        Long id = anyLong();

        Optional<Member> optional = Optional.ofNullable(new Member());
        Member actual = optional.get();
        actual.setId(id);
        actual.setDeleted(false);

        Member expected = new Member();
        expected.setId(id);
        expected.setDeleted(true);

        when(memberRepository.findById(id)).thenReturn(optional);

//        when
        memberDB.deleteByID(actual.getId());

//        then
        assertEquals(expected, actual);
    }
}