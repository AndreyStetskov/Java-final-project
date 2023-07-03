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

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final OfferRepository offerRepository;
    private final ConstructionSiteRepository constructionSiteRepository;
    private final MemberRepository memberRepository;


    @Scheduled(cron = "@daily")
    public void offerIrrelevant() {
        offerRepository.deactivateExpired();

        log.info("offer is irrelevant since "+
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
