package com.crazyemperor.construction_management.database.organisation;

import com.crazyemperor.construction_management.entity.Organisation;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;

import java.util.List;

/**
 * <b>Adding</b>, <b>retrieving</b> and <b>deleting</b> invoice.
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface OrganisationDataBaseService {

    /**
     * Adding some organisation
     * @param organisation - Some new organisation
     */
    Organisation addOrganisation(Organisation organisation);
    /**
     * Retrieving all organisations
     * @return List of existing organisations
     * @throws DataNotFoundException
     *          thrown if no organisations were found.
     */
    List<Organisation> getOrganisations();
    /**
     * Retrieving an organisation by ID
     * @param id - ID of an organisation to be found
     * @return An organisation by ID
     * @throws DataNotFoundException
     *          thrown if no organisation was found.
     */
    Organisation getByID(long id);
    /**
     * Deleting an organisation by name
     * @param name - name of an organisation to be deleted
     * @throws DataNotFoundException
     *          thrown if There's no such organisation name.
     */
    void deleteByName(String name);
    /**
     * Deleting an organisation by name
     * @param id - ID of a organisation to be deleted
     * @throws DataNotFoundException
     *          thrown if There's no such organisation ID.
     */
    void deleteByID(long id);
}
