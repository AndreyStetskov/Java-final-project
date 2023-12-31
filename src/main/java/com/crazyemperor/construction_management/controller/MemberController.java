package com.crazyemperor.construction_management.controller;

import com.crazyemperor.construction_management.crud.member.MemberCRUDService;
import com.crazyemperor.construction_management.entity.Member;
import com.crazyemperor.construction_management.service.member.MembersService;
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
    private final MembersService memberService;


    @PostMapping(value = "/create-new-member")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        memberCRUDService.add(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Member>> allMembers() {
        List<Member> members = memberCRUDService.getAllMembers();

        if (members != null && !members.isEmpty()) {
            return ResponseEntity.ok(members);
        }
        else return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable long id) {
        Member member = memberCRUDService.getMemberByID(id);

        return member != null ? ResponseEntity.ok(member) : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Long> deleteByID(@PathVariable long id) {
        memberCRUDService.deleteMemberByID(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/delete/{organisationName}")
    public ResponseEntity<Long> deleteByOrganisationName(@PathVariable String organisationName) {
        memberCRUDService.deleteMemberByOrganisationName(organisationName);

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
}
