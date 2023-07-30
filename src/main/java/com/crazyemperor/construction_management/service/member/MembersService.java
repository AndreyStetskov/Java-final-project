package com.crazyemperor.construction_management.service.member;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Member;

import java.util.List;

/**
 * <b>Retrieving list</b> of existing members with e-mail
 * <p><img src = "https://w7.pngwing.com/pngs/628/200/png-transparent-e-mail-logo-email-address-email-box-gmail-email-forwarding-email-miscellaneous-angle-logo.png" width = "250" height = "auto" alt = "money"></p>
 * @author Stetskov
 * @version 1.0
 */
public interface MembersService {

    /**
     * Retrieving all unpaid invoices
     * @return List of existing members who gave their e-mail address
     * @throws NoDataFoundException
     *          thrown if no one members with e-mail was found.
     */
    List<Member> getMembersWithGmail();
}
