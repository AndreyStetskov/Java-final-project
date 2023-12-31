package com.crazyemperor.construction_management.service.building;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.Offer;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <b>Calculation of a total amount</b> of construction site,
 * <p><img src = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQN_dB3R0ip55pFr8ZoNLX8iFrAO1KjsHZasg&usqp=CAU" alt = "money"></p>
 *  and <b>adding</b> organisation as some member to some construction site.
 * <p><img src = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbGicguNrSnx3qxM2fmsZ69hPB9A_o3x9DEQ&usqp=CAU" alt = "join us"></p>
 * @author Stetskov
 * @version 1.0
 */
public interface ConstructionSiteService {

    /**
     * Calculation of costs of all construction sites
     * @return A sum of all construction sites
     * @throws NoDataFoundException
     *          thrown if no construction sites were found.
     */
    BigDecimal getSumAllBuildings();
    /**
     * Calculation of costs of current construction sites
     * @return A sum of all construction sites
     * @throws NoDataFoundException
     *          thrown if no current construction sites were found.
     */
    BigDecimal getSumCurrentBuildings();
    /**
     * Calculation of costs of all construction sites which were registered after a certain date
     * @param date a single point in time that it is recommended to record the date as a year, then a month, then a day: YYYY-MM-DD.
     * @return A sum of all construction sites
     * @throws NoDataFoundException
     *          thrown if no construction sites were found.
     */
    BigDecimal getSumBuildingsAfterDate(LocalDate date);

    /**
     * Appoints a participant as a general constructor from a selected proposal and changes a status of the participant
     * @param bestProposal some selected offer
     * @throws NoDataFoundException
     *          thrown if no offer was found.
     * @throws IllegalArgumentException
     *          thrown if status of member isn't active.
     */
    void selectedConstructor(ConstructionSite constructor, Offer bestProposal);
    /**
     * Appoints a participant as a engineering organisation from a selected proposal and changes a status of the participant
     * @throws NoDataFoundException
     *          thrown if no offer was found.
     * @throws IllegalArgumentException
     *          thrown if status of member isn't active.
     */
    void selectedEngineering(ConstructionSite engineering, Offer bestProposal);
    /**
     * Appoints a participant as a protector from a selected proposal and changes a status of the participant
     * @throws NoDataFoundException
     *          thrown if no offer was found.
     * @throws IllegalArgumentException
     *          thrown if status of member isn't active.
     */
    void selectedProjector(ConstructionSite projector, Offer bestProposal);
}
