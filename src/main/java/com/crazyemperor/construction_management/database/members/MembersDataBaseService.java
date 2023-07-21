package com.crazyemperor.construction_management.database.members;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Member;

import java.util.List;

/**
 * <b>Adding</b>, <b>retrieving</b> and <b>deleting</b> invoice.
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface MembersDataBaseService {

    /**
     * Adding some member
     * @param member - A organisation as a member of building
     */
    Member addMember(Member member);
    /**
     * Retrieving all members
     * @return List of existing members
     * @throws NoDataFoundException
     *          thrown if no members were found.
     */
    List<Member> getMembers();
    /**
     * Retrieving a member by ID
     * @param id - ID of a member to be found
     * @return A member by ID
     * @throws NoDataFoundException
     *          thrown if no member was found.
     */
    Member getByID(long id);
    /**
     * Deleting a member by name
     * @param name - Name of a member to be deleted
     * @throws NoDataFoundException
     *          thrown if There's no such member ID.
     */
    void deleteByOrganisationName(String name);
    /**
     * Deleting a member by name
     * @param id - organisation ID of a member to be deleted
     * @throws NoDataFoundException
     *          thrown if There's no such member name.
     */
    void deleteByID(long id);
}
