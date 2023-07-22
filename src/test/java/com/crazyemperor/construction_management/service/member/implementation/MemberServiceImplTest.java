package com.crazyemperor.construction_management.service.member.implementation;

import com.crazyemperor.construction_management.entity.*;
import com.crazyemperor.construction_management.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MemberServiceImplTest extends com.crazyemperor.construction_management.Mock {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    void getMembersWithGmail_ReturnAllMembersWithGmail_Success() {

//        given
        List<Member> expected = new ArrayList<>();
        Member member = new Member();
        Member memberTwo = new Member();

        member.setDeleted(false);
        member.setOrganisation(new Organisation());
        member.getOrganisation().setEmail("java@gmail.com");
        expected.add(member);

        memberTwo.setDeleted(false);
        memberTwo.setOrganisation(new Organisation());
        memberTwo.getOrganisation().setEmail("unit@gmail.com");
        expected.add(memberTwo);

        when(memberRepository.findMembersWithGmailAddress()).thenReturn(expected);

//        when
        List<Member> actual = memberService.getMembersWithGmail();

//        then
        assertEquals(expected, actual);
    }
}