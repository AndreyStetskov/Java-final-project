package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.member.MemberCRUDService;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.entity.Organisation;
import com.crazyemperor.construction_management.service.member.MembersService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class MemberControllerTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private MemberCRUDService memberCRUDService;
    @Mock
    private MembersService memberService;

    @InjectMocks
    private MemberController memberController;

    private final Member member = new Member();
    private final List<Member> memberList = new ArrayList<>();


    @Test
    void createMember_AddNewMember_Success() {

//        given
        ResponseEntity<Member> expected = new ResponseEntity<>(member, HttpStatus.CREATED);

        when(memberCRUDService.add(any())).thenReturn(expected.getBody());

//        when
        ResponseEntity<Member> actual = memberController.createMember(member);

//        then
        assertEquals(expected, actual);
        verify(memberCRUDService, times(1)).add(member);
    }

    @Test
    void allMembers_ReturnListOfAllMembersOfThreeObjects_Success() {

//        given
        memberList.add(member);
        memberList.add(new Member());
        memberList.add(new Member());

        ResponseEntity<List<Member>> expected = new ResponseEntity<>(memberList, HttpStatus.OK);

        when(memberCRUDService.getAllMembers()).thenReturn(memberList);

//        when
        ResponseEntity<List<Member>> actual = memberController.allMembers();

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getMemberById_ReturnMemberById_Success() {

//        given
        long idMember = anyLong();
        member.setId(idMember);

        ResponseEntity<Member> expected = new ResponseEntity<>(member, HttpStatus.OK);

        when(memberCRUDService.getMemberByID(idMember)).thenReturn(member);

//        when
        ResponseEntity<Member> actual = memberController.getMemberById(idMember);

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByID_ChangeDeleteStatusToTrue_Success() {

//        given
        member.setDeleted(true);

        ResponseEntity<Long> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<Long> actual = memberController.deleteByID(anyLong());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void deleteByOrganisationName_ChangeDeleteStatusToTrue_Success() {

//        given
        member.setDeleted(true);

        ResponseEntity<Long> expected = new ResponseEntity<>(HttpStatus.OK);

//        when
        ResponseEntity<Long> actual = memberController.deleteByOrganisationName(anyString());

//        then
        assertEquals(expected, actual);
    }

    @Test
    void getWithGmail_ReturnListOfAllMembersOfThreeObjectsWithGmail_Success() {

//        given
        member.setOrganisation(new Organisation());
        member.getOrganisation().setEmail("lox@gmail.com");
        memberList.add(member);

        Member memberTwo = new Member();
        memberTwo.setOrganisation(new Organisation());
        memberTwo.getOrganisation().setEmail("java@gmail.com");
        memberList.add(memberTwo);

        Member memberThree = new Member();
        memberThree.setOrganisation(new Organisation());
        memberThree.getOrganisation().setEmail("junit@gmail.com");
        memberList.add(memberThree);

        ResponseEntity<List<Member>> expected = new ResponseEntity<>(memberList, HttpStatus.OK);

        when(memberService.getMembersWithGmail()).thenReturn(memberList);

//        when
        ResponseEntity<List<Member>> actual = memberController.getWithGmail();

//        then
        assertEquals(expected, actual);
    }
}