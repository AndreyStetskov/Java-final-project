package com.crazyemperor.construction_management.service.member.implementation;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.repository.MemberRepository;
import com.crazyemperor.construction_management.repository.PaymentRepository;
import com.crazyemperor.construction_management.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @SneakyThrows
    @Override
    @Transactional
    public List<Member> getMembersWithGmail() {
        Optional<List<Member>> members = Optional.ofNullable(memberRepository.findMembersWithGmailAddress());

        return members
                .orElseThrow(() -> {
                    new NoDataFoundException("No one member with gmail found");
                    return null;
                });
    }
}
