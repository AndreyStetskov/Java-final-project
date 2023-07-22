package com.crazyemperor.construction_management.database.members.implementation;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.database.members.MembersDataBaseService;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberDataBaseImpl implements MembersDataBaseService {

    private final MemberRepository memberRepository;


    @Override
    @CacheEvict("members")
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    @Cacheable("members")
    public List<Member> getMembers() {
        Optional<List<Member>> members = Optional.of(memberRepository.findAll());

        return members
                .orElseThrow(() -> {
                    new NoDataFoundException("No one member found");
                    return null;
                });
    }


    @Override
    @Cacheable("members")
    public Member getByID(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> {
                    new NoDataFoundException(String.format("No member found for id %d", id));
                    return null;
                });
    }

    @Override
    @CacheEvict("members")
    public void deleteByOrganisationName(String name) {
        Optional<Member> memberOptional = Optional.ofNullable(memberRepository.findByOrganisationName(name));
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setDeleted(true);
            memberRepository.save(member);
        }
        else try {
            throw new NoDataFoundException(String.format("No member found for id %s", name));
        } catch (NoDataFoundException e) {
            throw new IllegalArgumentException("NoDataFoundException didn't work", e);
        }
    }

    @Override
    @CacheEvict("members")
    public void deleteByID(long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> {
                    try {
                        throw new NoDataFoundException(String.format("No member found for oid %d", id));
                    } catch (NoDataFoundException e) {
                        throw new IllegalArgumentException("NoDataFoundException didn't work", e);
                    }
                });

        member.setDeleted(true);
        memberRepository.save(member);
    }
}
