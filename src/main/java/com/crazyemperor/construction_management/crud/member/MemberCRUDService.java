package com.crazyemperor.construction_management.crud.member;

import com.crazyemperor.construction_management.entity.Member;

import java.util.List;

public interface MemberCRUDService {

    Member add(Member member);
    List<Member> getAllMembers();
    Member getMemberByID(int id);
    Member deleteMemberByOrganisation(Long id, Member delete);
    Member deleteMemberByID(int id, Member delete);
}
