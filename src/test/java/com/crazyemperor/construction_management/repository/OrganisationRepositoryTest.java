package com.crazyemperor.construction_management.repository;

import com.crazyemperor.construction_management.entity.Organisation;
import com.crazyemperor.construction_management.entity.auxillirary.Department;
import com.crazyemperor.construction_management.entity.auxillirary.OrganisationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class OrganisationRepositoryTest {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Test
    void findByName() {

//        given
        Organisation expected = new Organisation();
        Organisation organisation = new Organisation();

        expected.setEin("46-4545464");
        expected.setName("Pupkin and Ko");
        expected.setDepartment(Department.ENERGY);
        expected.setRegistration(LocalDate.of(1895,1,30));
        expected.setLocation("Address");
        expected.setStatus(OrganisationStatus.ACTIVE);

        organisation.setEin("14-3182558");
        organisation.setName("National School");
        organisation.setDepartment(Department.ENERGY);
        organisation.setRegistration(LocalDate.of(2000,10,30));
        organisation.setLocation("Some address");
        organisation.setStatus(OrganisationStatus.ACTIVE);

        organisationRepository.save(expected);
        organisationRepository.save(organisation);

//        when
        Organisation actual = organisationRepository.findByName("Pupkin and Ko");

//        then
        assertEquals(expected, actual);
    }
}