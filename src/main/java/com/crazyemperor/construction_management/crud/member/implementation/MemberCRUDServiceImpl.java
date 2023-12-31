package com.crazyemperor.construction_management.crud.member.implementation;

import com.crazyemperor.construction_management.database.members.MembersDataBaseService;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.crud.member.MemberCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberCRUDServiceImpl implements MemberCRUDService {

    @Autowired
    private MembersDataBaseService membersDBService;


    @Override
    public Member add(Member member) {
        return membersDBService.addMember(member);
    }

    @Override
    public List<Member> getAllMembers() {
        return membersDBService.getMembers();
    }

    @Override
    public Member getMemberByID(long id) {
        return membersDBService.getByID(id);
    }

    @Override
    public void deleteMemberByOrganisationName(String name) {
        membersDBService.deleteByOrganisationName(name);
    }

    @Override
    public void deleteMemberByID(long id) {
        membersDBService.deleteByID(id);
    }
}
