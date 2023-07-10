package com.crazyemperor.construction_management.crud.member;

import com.crazyemperor.construction_management.entity.Member;

import java.util.List;

public interface MemberCRUDService {

    Member add(Member member);
    List<Member> getAllMembers();
    Member getMemberByID(long id);
    void deleteMemberByOrganisation(Long id);
    void deleteMemberByID(long id);
}
