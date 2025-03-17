package dding.band.controller;

import dding.band.dto.request.BandRecruitPositionRequest;
import dding.band.dto.response.BandRecruitPositionResponse;
import dding.band.service.BandRecruitPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bands/recruit-positions")
@RequiredArgsConstructor
public class BandRecruitPositionController {

    private final BandRecruitPositionService positionService;

    @PostMapping
    public ResponseEntity<String> addRecruitPosition(@RequestBody BandRecruitPositionRequest request) {
        positionService.addRecruitPosition(request);
        return ResponseEntity.ok("모집 포지션이 추가되었습니다.");
    }

    @GetMapping("/{bandId}")
    public ResponseEntity<List<BandRecruitPositionResponse>> getPositions(@PathVariable String bandId) {
        return ResponseEntity.ok(positionService.getPositions(bandId));
    }

    @PutMapping("/close/{positionId}")
    public ResponseEntity<String> closePosition(@PathVariable Long positionId) {
        positionService.closePosition(positionId);
        return ResponseEntity.ok("모집 포지션이 마감 처리되었습니다.");
    }
}