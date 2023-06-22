package com.crazyemperor.construction_management.database.members.implementation;

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
    @CacheEvict("Members")
    public Member addMember(Member constructionSite) {
        return memberRepository.save(constructionSite);
    }

    @Override
    @Cacheable("Members")
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    @Cacheable("Members")
    public Member getByID(long id) {
        return memberRepository.findById(id);
    }

    @Override
    @CacheEvict("Members")
    public Member deleteByOrganisation(long id, Member update) {
        Optional<Member> memberOptional = Optional.ofNullable(memberRepository.findByOrganisationId(id));
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setDeleted(true);
            memberRepository.save(member);
        }
        return memberOptional.orElse(null);
    }

    @Override
    @CacheEvict("Members")
    public Member deleteByID(long id, Member update) {
        Optional<Member> memberOptional = Optional.ofNullable(memberRepository.findById(id));
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setDeleted(true);
            memberRepository.save(member);
        }
        return memberOptional.orElse(null);
    }
}
