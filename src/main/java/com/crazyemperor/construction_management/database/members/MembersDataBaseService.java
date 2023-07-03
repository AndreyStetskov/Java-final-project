package com.crazyemperor.construction_management.database.members;

import com.crazyemperor.construction_management.entity.Member;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;

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
     * @throws DataNotFoundException
     *          thrown if no members were found.
     */
    List<Member> getMembers();
    /**
     * Retrieving a member by ID
     * @param id - ID of a member to be found
     * @return A member by ID
     * @throws DataNotFoundException
     *          thrown if no member was found.
     */
    Member getByID(long id);
    /**
     * Deleting a member by name
     * @param id - ID of a member to be deleted
     * @throws DataNotFoundException
     *          thrown if There's no such member ID.
     */
    void deleteByOrganisation(long id);
    /**
     * Deleting a member by name
     * @param id - organisation ID of a member to be deleted
     * @throws DataNotFoundException
     *          thrown if There's no such member name.
     */
    void deleteByID(long id);
}
