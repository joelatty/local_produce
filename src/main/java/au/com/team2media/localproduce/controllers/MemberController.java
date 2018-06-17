package au.com.team2media.localproduce.controllers;

import au.com.team2media.localproduce.exception.ResourceNotFoundException;
import au.com.team2media.localproduce.model.Member;
import au.com.team2media.localproduce.model.Profile;
import au.com.team2media.localproduce.services.MemberRepository;
import au.com.team2media.localproduce.services.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MemberController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProfileRepository profileRepository;

    @GetMapping("/members")
    public Page<Member> getMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @RequestMapping(value = "/member/create", method = RequestMethod.POST)
    public Member createMember(@Valid @RequestBody Member member) {
        return memberRepository.save(member);
    }


    @GetMapping("/member/{memberId}/profiles")
    public List<Profile> getProfilesByMemberId(@PathVariable Long memberId) {
        return profileRepository.findByMemberMemberId(memberId);
    }

    @RequestMapping(value = "/member/{memberId}/profile", method = RequestMethod.POST)
    public Profile createProfile(@PathVariable Long memberId,
                                 @Valid @RequestBody Profile profile) {
        return memberRepository.findById(memberId)
                .map(member -> {
                    // Create a join between the memeber and this profile
                    profile.setMember(member);
                    return profileRepository.save(profile);
                }).orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberId));
    }



    @RequestMapping(value = "/member/{memberId}", method = RequestMethod.PUT)
    public Member updateMember(@PathVariable Long memberId,
                                   @Valid @RequestBody Member memberRequest) {
        return memberRepository.findById(memberId)
                .map(member -> {
                    member.setName(memberRequest.getName());
                    member.setEmail(memberRequest.getEmail());
                    return memberRepository.save(member);
                }).orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberId));
    }


    @RequestMapping(value = "/member/{memberId}", method =  RequestMethod.DELETE)
    public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {
        return memberRepository.findById(memberId)
                .map(member -> {
                    memberRepository.delete(member);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberId));
    }
}
