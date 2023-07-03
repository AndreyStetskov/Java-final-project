package com.crazyemperor.construction_management.database.members.implementation;

import com.crazyemperor.construction_management.database.members.MembersDataBaseService;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.repository.MemberRepository;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    public Member addMember(Member constructionSite) {
        return memberRepository.save(constructionSite);
    }

    @SneakyThrows
    @Override
    @Cacheable("members")
    public List<Member> getMembers() {
        Optional<List<Member>> members = Optional.of(memberRepository.findAll());

        return members
                .orElseThrow(DataNotFoundException::new);
    }

    @SneakyThrows
    @Override
    @Cacheable("members")
    public Member getByID(long id) {
        return memberRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    @SneakyThrows
    @Override
    @CacheEvict("members")
    public void deleteByOrganisation(long id) {
        Optional<Member> memberOptional = Optional.ofNullable(memberRepository.findByOrganisationId(id));
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setDeleted(true);
            memberRepository.save(member);
        }
        else throw new DataNotFoundException();
    }

    @SneakyThrows
    @Override
    @CacheEvict("members")
    public void deleteByID(long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);

        member.setDeleted(true);
        memberRepository.save(member);
    }
}
