package com.crazyemperor.construction_management.database.organisation;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Organisation;

import java.util.List;

/**
 * <b>Adding</b>, <b>retrieving</b> and <b>deleting</b> organisation.
 * <p><img src = "https://bs-uploads.toptal.io/blackfish-uploads/components/seo/content/og_image_file/og_image/1282569/0712-Bad_Practices_in_Database_Design_-_Are_You_Making_These_Mistakes_Dan_Social-754bc73011e057dc76e55a44a954e0c3.png" width = "500" height = "auto" alt = "database"></p>
 * @author Stetskov
 * @version 1.0
 */
public interface OrganisationDataBaseService {

    /**
     * Adding some organisation
     * @param organisation some new organisation
     */
    Organisation addOrganisation(Organisation organisation);
    /**
     * Retrieving all organisations
     * @return List of existing organisations
     * @throws NoDataFoundException
     *          thrown if no organisations were found.
     */
    List<Organisation> getOrganisations();
    /**
     * Retrieving an organisation by ID
     * @param id ID of an organisation to be found
     * @return An organisation by ID
     * @throws NoDataFoundException
     *          thrown if no organisation was found.
     */
    Organisation getByID(long id);
    /**
     * Deleting an organisation by name
     * @param name name of an organisation to be deleted
     * @throws NoDataFoundException
     *          thrown if there's no such organisation name.
     */
    void deleteByName(String name);
    /**
     * Deleting an organisation by name
     * @param id ID of a organisation to be deleted
     * @throws NoDataFoundException
     *          thrown if there's no such organisation ID.
     */
    void deleteByID(long id);
}
