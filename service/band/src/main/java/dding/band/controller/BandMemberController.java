package dding.band.controller;

import dding.band.config.eums.BandPosition;
import dding.band.entity.BandMember;
import dding.band.service.BandMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bands/members")
@RequiredArgsConstructor
public class BandMemberController {

    private final BandMemberService bandMemberService;

    @PostMapping
    public ResponseEntity<String> addMember(@RequestParam String bandId,
                                            @RequestParam String userId,
                                            @RequestParam BandPosition position) {
        bandMemberService.addMember(bandId, userId, position);
        return ResponseEntity.ok("구성원이 추가되었습니다.");
    }

    @GetMapping("/band/{bandId}")
    public ResponseEntity<List<BandMember>> getMembersByBand(@PathVariable String bandId) {
        return ResponseEntity.ok(bandMemberService.getMembersByBand(bandId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BandMember>> getBandsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(bandMemberService.getBandsByUser(userId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> removeMember(@PathVariable Long memberId) {
        bandMemberService.removeMember(memberId);
        return ResponseEntity.ok("구성원이 밴드에서 삭제되었습니다.");
    }
}