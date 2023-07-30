package com.crazyemperor.construction_management.schedule;

import com.crazyemperor.construction_management.entity.ConstructionSite;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.entity.Offer;
import com.crazyemperor.construction_management.entity.auxillirary.ConstructionSiteStatus;
import com.crazyemperor.construction_management.entity.auxillirary.MemberType;
import com.crazyemperor.construction_management.entity.auxillirary.OfferStatus;
import com.crazyemperor.construction_management.repository.ConstructionSiteRepository;
import com.crazyemperor.construction_management.repository.MemberRepository;
import com.crazyemperor.construction_management.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

/**
 * <b>Inactive</b> offers that have expired
 * <p><img src = "data:image/png;base64,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" width = "250" height = "auto" alt = "money"></p>
 * and closing buildings that finished
 * <p><img src = "https://t4.ftcdn.net/jpg/02/99/56/73/360_F_299567331_q4vICKYkfQ6qLHvw4d75uBXaGwJq0bkg.jpg" width = "250" height = "auto" alt = "money"></p>
 * @author Stetskov
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final OfferRepository offerRepository;
    private final ConstructionSiteRepository constructionSiteRepository;
    private final MemberRepository memberRepository;


    /**
     * Makes inactive offers that have expired
     */
    @Scheduled(cron = "@daily")
    public void offerIrrelevant() {
        offerRepository.deactivateExpired();

        log.info("offer is irrelevant since " +
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    }

    @SneakyThrows
    @Scheduled(cron = "@daily")
    @Transactional
    public void buildingClosed() {
        List<Offer> buildingList = offerRepository.findWithOfferAndMembers();

        if (buildingList.isEmpty()) return;

        buildingList.forEach(building -> {
            Optional<ConstructionSite> constructionSiteOptional = constructionSiteRepository.findById(building.getOffer().getId());

            ConstructionSite constructionSite = constructionSiteOptional.get();
            constructionSite.setStatus(ConstructionSiteStatus.CLOSED);

            constructionSiteRepository.save(constructionSite);
            log.info("Construction site " + constructionSite.getTitle() + " was closed at " +
                    LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
            );


            building.setStatus(OfferStatus.DENIED);
            log.info("Offer " + building.getTitle() + " was closed at " +
                    LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) +
                    "because construction site " + constructionSite.getTitle() + " was closed"
            );


            Optional<Member> constructorOptional = memberRepository.findById(building.getOffer().getConstructor().getId());

            if (constructorOptional.isPresent()) {
                Member constructor = constructorOptional.get();
                constructor.getType().remove(MemberType.CONSTRUCTOR);

                log.info("Member " + constructor.getOrganisation().getName() + " isn't constructor at " +
                        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) +
                        "because construction site " + constructionSite.getTitle() + " was closed"
                );
            }

            Optional<Member> engineeringOptional = memberRepository.findById(building.getOffer().getEngineering().getId());

            if (engineeringOptional.isPresent()) {
                Member engineering = engineeringOptional.get();
                engineering.getType().remove(MemberType.CONSTRUCTOR);

                log.info("Member " + engineering.getOrganisation().getName() + " isn't engineering at " +
                        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) +
                        "because construction site " + constructionSite.getTitle() + " was closed"
                );
            }

            Optional<Member> protectorOptional = memberRepository.findById(building.getOffer().getProjector().getId());

            if (protectorOptional.isPresent()) {
                Member protector = protectorOptional.get();
                protector.getType().remove(MemberType.CONSTRUCTOR);

                log.info("Member " + protector.getOrganisation().getName() + " isn't protector at " +
                        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) +
                        "because construction site " + constructionSite.getTitle() + " was closed"
                );
            }
        });
    }
}
