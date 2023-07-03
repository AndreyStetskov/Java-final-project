package com.crazyemperor.construction_management.database.offer;

import com.crazyemperor.construction_management.entity.Offer;
import com.ho1ho.springboot.framework.core.exceptions.DataNotFoundException;

import java.util.List;

/**
 * <b>Adding</b>, <b>retrieving</b> and <b>deleting</b> invoice.
 * {@code @autor} Stetskov
 * @version 1.0
 */
public interface OfferDataBaseService {

    /**
     * Adding some offer
     * @param offer - Some proposal from one building member to another
     */
    Offer addOffer(Offer offer);
    /**
     * Retrieving all offers
     * @return List of existing offers
     * @throws DataNotFoundException
     *          thrown if no offers were found.
     */
    List<Offer> getOffers();
    /**
     * Retrieving an offer by ID
     * @param id - ID of an offer to be found
     * @return An offer by ID
     * @throws DataNotFoundException
     *          thrown if no offer was found.
     */
    Offer getByID(long id);
    /**
     * Retrieving an offer by ID
     * @return An offer by ID
     * @throws DataNotFoundException
     *          thrown if no offer was found.
     */
    Offer getByTitle(String name);
    /**
     * Deleting an offer by name
     * @param name - name of an offer to be deleted
     * @throws DataNotFoundException
     *          thrown if There's no such offer name.
     */
    void deleteByName(String name);
    /**
     * Deleting an offer by name
     * @param id - ID of a offer to be deleted
     * @throws DataNotFoundException
     *          thrown if There's no such offer ID.
     */
    void deleteByID(long id);
}
