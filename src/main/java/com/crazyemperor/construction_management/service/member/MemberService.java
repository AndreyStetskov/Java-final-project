package com.crazyemperor.construction_management.service.member;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Member;

import java.util.List;

/**
 * <b>Retrieving list</b> of existing members with e-mail and of a members who paid anything
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface MemberService {

    /**
     * Retrieving all unpaid invoices
     * @return List of existing members who gave their e-mail address
     * @throws NoDataFoundException
     *          thrown if no one members with e-mail was found.
     */
    List<Member> getMembersWithGmail();
}
