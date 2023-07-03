package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.member.MemberCRUDService;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCRUDService memberCRUDService;
    private final MemberService memberService;


    @PostMapping(value = "/create-new-member")
    public ResponseEntity<Member> createInvoice(@RequestBody Member member) {
        memberCRUDService.add(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Member>> allInvoices() {
        List<Member> members = memberCRUDService.getAllMembers();

        if (members != null && !members.isEmpty()) {
            return ResponseEntity.ok(members);
        }
        else return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable int id) {
        Member member = memberCRUDService.getMemberByID(id);

        return member != null ? ResponseEntity.ok(member) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Long> deleteByID(@PathVariable int id) {
        memberCRUDService.deleteMemberByID(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/delete/{organisationId}")
    public ResponseEntity<Long> deleteByOrganisation(@PathVariable long organisationId) {
        memberCRUDService.deleteMemberByOrganisation(organisationId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/find/with-gmail")
    public ResponseEntity<List<Member>> getWithGmail() {
        List<Member> memberWithGmail = memberService.getMembersWithGmail();

        if (memberWithGmail != null && !memberWithGmail.isEmpty()) {
            return ResponseEntity.ok(memberWithGmail);
        }
        else return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/all/paid")
    public ResponseEntity<List<Member>> getPaid() {
        List<Member> allOrganisations = memberService.geAllPaidOrganisations();

        if (allOrganisations != null && !allOrganisations.isEmpty()) {
            return ResponseEntity.ok(allOrganisations);
        }
        else return ResponseEntity.noContent().build();
    }
}
