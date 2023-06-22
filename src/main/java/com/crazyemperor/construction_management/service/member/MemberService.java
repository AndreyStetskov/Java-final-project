package com.crazyemperor.construction_management.service.member;

import com.crazyemperor.construction_management.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> getMembersWithGmail();
    List<Member> geAllPaidOrganisations();
}
