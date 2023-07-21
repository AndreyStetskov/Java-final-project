package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.ConstructionSite;

import com.crazyemperor.construction_management.entity.auxillirary.ConstructionSiteStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ConstructionSiteRepositoryTest {

    @Autowired
    private ConstructionSiteRepository constructionSiteRepository;

    @Test
    void findByTitle_ReturnConstructionSiteByName_Success() {

//        given
        ConstructionSite expected = new ConstructionSite();
        ConstructionSite constructionSite = new ConstructionSite();

        expected.setTitle("Skyscraper WC");
        expected.setAmount(new BigDecimal("10230000"));
        expected.setStatus(ConstructionSiteStatus.ACTIVE);

        constructionSite.setTitle("Some house");
        constructionSite.setAmount(new BigDecimal("100"));
        constructionSite.setStatus(ConstructionSiteStatus.ACTIVE);

        constructionSiteRepository.save(expected);
        constructionSiteRepository.save(constructionSite);


//        when
        ConstructionSite actual = constructionSiteRepository.findByTitle("Skyscraper WC");

//        then
        assertEquals(expected, actual);
    }
}