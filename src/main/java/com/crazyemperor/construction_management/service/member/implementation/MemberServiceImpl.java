package com.crazyemperor.construction_management.service.member.implementation;

import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.repository.MemberRepository;
import com.crazyemperor.construction_management.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @Override
    public List<Member> getMembersWithGmail() {
        return memberRepository.findMembersWithGmailAddress();
    }

    @Override
    public List<Member> geAllPaidOrganisations() {
        return memberRepository.findAllPaidMembers();
    }
}
