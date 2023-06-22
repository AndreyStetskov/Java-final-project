package com.crazyemperor.construction_management.database.members;

import com.crazyemperor.construction_management.entity.Member;

import java.util.List;

public interface MembersDataBaseService {

    Member addMember(Member member);
    List<Member> getMembers();
    Member getByID(long id);
    Member deleteByOrganisation(long id, Member update);
    Member deleteByID(long id, Member update);
}
