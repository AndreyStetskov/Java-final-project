package com.crazyemperor.construction_management.service.offer;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Offer;

import java.math.BigDecimal;
import java.util.List;

/**
 * <b>Retrieving offer</b> that contains the cheapest cost or the fastest construction time
 * <p><img src = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKlfCC9PWRpjNw1SpHjf5P48ujdDQ7KINLqixMdNHccoHwrNWDjMyvcf8QiziyOK8H2Bo&usqp=CAU" width = "250" height = "auto" alt = "money"></p>
 * @author Stetskov
 * @version 1.0
 */
public interface OfferService {

    /**
     * Retrieving an offer that contains the cheapest cost
     * @param id ID of members who finding the cheapest offer
     * @return An offer with the cheapest cost
     * @throws NoDataFoundException
     *          thrown if no one offer was found.
     */
    Offer getCheapest(long id);
    /**
     * Retrieving an offer that contains the fastest construction time
     * @param id ID of members who finding the least deadline
     * @return An offer with the least deadline
     * @throws NoDataFoundException
     *          thrown if no one offer was found.
     */
    Offer getFastest(long id);

    /**
     * Retrieving an offer that contains the cheapest cost less than a certain amount
     * @param id ID of members who finding the cheapest offer less than a certain amount
     * @param amount maximum value of a sum
     * @return An offer with the cheapest cost
     * @throws NoDataFoundException
     *          thrown if no one offer less than a certain amount was found.
     */
    List<Offer> getCheaperThan(long id, BigDecimal amount);
    /**
     * Retrieving an offer that contains the fastest construction time which faster than a certain term
     * @param id ID of members who finding the least deadline faster than a certain term
     * @param days maximum production period in days
     * @return An offer with the least deadline
     * @throws NoDataFoundException
     *          thrown if no one offer faster than a certain term was found.
     */
    List<Offer> getFasterThen(long id, int days);
}
