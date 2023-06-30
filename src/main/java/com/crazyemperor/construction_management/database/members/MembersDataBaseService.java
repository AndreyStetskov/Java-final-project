package com.crazyemperor.construction_management.database.members;

import com.crazyemperor.construction_management.entity.Member;

import java.util.List;

public interface MembersDataBaseService {

    Member addMember(Member member);
    List<Member> getMembers();
    Member getByID(long id);
    void deleteByOrganisation(long id);
    void deleteByID(long id);
}
