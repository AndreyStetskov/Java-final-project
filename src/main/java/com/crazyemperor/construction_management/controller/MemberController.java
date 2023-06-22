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


    @PostMapping(value = "/create_new_member")
    public ResponseEntity<Member> createInvoice(@RequestBody Member member) {
        memberCRUDService.add(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Member>> allInvoices() {
        List<Member> members = memberCRUDService.getAllMembers();

        return members != null ? ResponseEntity.ok(members) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable int id) {
        Member member = memberCRUDService.getMemberByID(id);

        return member != null ? ResponseEntity.ok(member) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Member> deleteByID(@PathVariable int id, @RequestBody Member member) {
        Member deactivate = memberCRUDService.deleteMemberByID(id, member);

        return member != null ? ResponseEntity.ok(deactivate) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{organisation_id}")
    public ResponseEntity<Member> deleteByOrganisation(@PathVariable long organisation_id, @RequestBody Member member) {
        Member deactivated = memberCRUDService.deleteMemberByOrganisation(organisation_id, member);

        return member != null ? ResponseEntity.ok(deactivated) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/with_gmail")
    public ResponseEntity<List<Member>> getWithGmail() {
        List<Member> memberWithGmail = memberService.getMembersWithGmail();

        return memberWithGmail != null ? ResponseEntity.ok(memberWithGmail) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/all/paid")
    public ResponseEntity<List<Member>> getPaid() {
        List<Member> memberWithGmail = memberService.geAllPaidOrganisations();

        return memberWithGmail != null ? ResponseEntity.ok(memberWithGmail) : ResponseEntity.noContent().build();
    }
}
