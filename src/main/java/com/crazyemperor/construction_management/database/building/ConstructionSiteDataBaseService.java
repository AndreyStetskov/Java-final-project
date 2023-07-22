package com.crazyemperor.construction_management.database.building;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.ConstructionSite;

import java.util.List;

/**
 * <b>Adding</b>, <b>retrieving</b> and <b>deleting</b> construction site.
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface ConstructionSiteDataBaseService {

    /**
     * Adding construction some site
     * @param constructionSite - A object of construction
     */
    void addConstructionSite(ConstructionSite constructionSite);
    /**
     * Retrieving all construction sites
     * @return List of existing construction sites
     * @throws NoDataFoundException
     *          thrown if no construction sites were found.
     */
    List<ConstructionSite> getConstructionSites();
    /**
     * Retrieving a construction site by ID
     * @param id - ID of a construction site to be found
     * @return A construction site by ID
     * @throws NoDataFoundException
     *          thrown if no construction site was found.
     */
    ConstructionSite getByID(long id);
    /**
     * Deleting a construction si- ID of a construction site to be deleted by name
     * @param name - name of a construction site to be deleted
     * @throws NoDataFoundException
     *          thrown if There's no such construction site name.
     */
    void deleteByName(String name);
    /**
     * Deleting a construction site by name
     * @param id - ID of a construction site to be deleted
     * @throws NoDataFoundException
     *          thrown if There's no such construction site ID.
     */
    void deleteByID(long id);
}
