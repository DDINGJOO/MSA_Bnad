package dding.band.controller;


import dding.band.dto.request.BandCreateRequest;
import dding.band.dto.request.BandMemberJoinRequest;
import dding.band.dto.response.BandMemberResponse;
import dding.band.dto.response.BandResponse;
import dding.band.entity.BandMember;
import dding.band.service.BandService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/band")
public class BandController {
    private final BandService bandService;
    @PostMapping()
    public ResponseEntity<String> createBand(
            @RequestBody BandCreateRequest req
            ) {

        // Band 생성 로직
        bandService.createBand(req);
        return ResponseEntity.ok("Band created successfully");
    }



    //전체 밴드 리스트
    @GetMapping("/list")
    public ResponseEntity<Page<BandResponse>> getBandList(
            @RequestParam(required = false) String userId,
            @PageableDefault(size = 10, sort = "createdAt") Pageable page
    ) {
        if(userId != null)
        {
            return ResponseEntity.ok(bandService.getMyBandList(userId, page));
        }
        return ResponseEntity.ok(bandService.getBandList(page));
    }



    // 밴드 디테일
    @GetMapping()
    public ResponseEntity<BandResponse> getBandDetail(
            @RequestParam (required = true) String bandId
    ) {
        BandResponse bandResponse = bandService.getBandDetail(bandId);
        return ResponseEntity.ok(bandResponse);
    }


    //밴드 삭제
    @DeleteMapping()
    public ResponseEntity<String> deleteBand(
            @RequestParam (required = true) String bandId
    ) {
        bandService.deleteBand(bandId);
        return ResponseEntity.ok("Band deleted successfully");
    }


    @DeleteMapping("/{bandId}/{userId}")
    public ResponseEntity<String> deleteBandMember(
            @PathVariable(name = "bandId") String bandId,
            @PathVariable(name = "userId") String userId
    ) {
        bandService.leaveBand(bandId, userId);
        return ResponseEntity.ok("Band member deleted successfully");
    }


    //맴버
    @GetMapping("/members")
    public Page<BandMemberResponse> getBandMembers(
            @RequestParam (required = true) String bandId,
            @PageableDefault (size = 10, sort = "joinedAt") Pageable page
    ) {
        return bandService.getBandMembers(bandId, page);
    }

    //지원자
    @GetMapping("/members/req")
    public Page<BandMemberResponse> getReqBandMembers(
            @RequestParam (required = true) String bandId,
            @PageableDefault (size = 10, sort = "joinedAt") Pageable page
    ) {
        return bandService.getNotConfirmedBandMembers(bandId, page);
    }


    @PostMapping("/reqBandMember")
    public ResponseEntity<?> reqBandMember(
            @RequestBody BandMemberJoinRequest req
    ) {
        return ResponseEntity.ok(bandService.requestBandMember(req));

    }





    @PutMapping("/confirmed/{leaderId}/{userId}")
    public ResponseEntity<String> acceptBandMember(
            @PathVariable(name = "leaderId") String leaderId,
            @PathVariable(name = "userId") String userId
    ) {
        bandService.confirmed(leaderId, userId);
        return ResponseEntity.ok("Band member accepted successfully");
    }

    @PutMapping("/reject/{leaderId}/{userId}")
    public ResponseEntity<String> rejectBandMember(
            @PathVariable(name = "leaderId") String leaderId,
            @PathVariable(name = "userId") String userId
    ) {
        bandService.reject(leaderId, userId);
        return ResponseEntity.ok("Band member rejected successfully");
    }

}
