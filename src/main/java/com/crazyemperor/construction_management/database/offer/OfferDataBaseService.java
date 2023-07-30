package com.crazyemperor.construction_management.database.offer;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Offer;

import java.util.List;

/**
 * <b>Adding</b>, <b>retrieving</b> and <b>deleting</b> offer.
 * <p><img src = "https://bs-uploads.toptal.io/blackfish-uploads/components/seo/content/og_image_file/og_image/1282569/0712-Bad_Practices_in_Database_Design_-_Are_You_Making_These_Mistakes_Dan_Social-754bc73011e057dc76e55a44a954e0c3.png" width = "500" height = "auto" alt = "database"></p>
 * @author Stetskov
 * @version 1.0
 */
public interface OfferDataBaseService {

    /**
     * Adding some offer
     * @param offer some proposal from one building member to another
     */
    Offer addOffer(Offer offer);
    /**
     * Retrieving all offers
     * @return List of existing offers
     * @throws NoDataFoundException
     *          thrown if no offers were found.
     */
    List<Offer> getOffers();
    /**
     * Retrieving an offer by ID
     * @param id ID of an offer to be found
     * @return An offer by ID
     * @throws NoDataFoundException
     *          thrown if no offer was found.
     */
    Offer getByID(long id);
    /**
     * Retrieving an offer by ID
     * @return An offer by ID
     * @throws NoDataFoundException
     *          thrown if no offer was found.
     */
    Offer getByTitle(String name);
    /**
     * Deleting an offer by name
     * @param name name of an offer to be deleted
     * @throws NoDataFoundException
     *          thrown if there's no such offer name.
     */
    void deleteByName(String name);
    /**
     * Deleting an offer by name
     * @param id ID of a offer to be deleted
     * @throws NoDataFoundException
     *          thrown if there's no such offer ID.
     */
    void deleteByID(long id);
}
