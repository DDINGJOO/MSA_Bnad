package dding.band.controller;

import dding.band.config.eums.BandCategory;
import dding.band.config.eums.BandPosition;
import dding.band.dto.request.BandCreateRequest;
import dding.band.dto.request.BandUpdateRequest;
import dding.band.dto.response.BandResponse;
import dding.band.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bands")
@RequiredArgsConstructor
public class BandController {

    private final BandService bandService;

    @PostMapping
    public ResponseEntity<String> createBand(@RequestBody BandCreateRequest request) {
        String bandId = bandService.createBand(request);
        return ResponseEntity.ok(bandId);
    }

    @GetMapping("/{bandId}")
    public ResponseEntity<BandResponse> getBand(@PathVariable String bandId) {
        return ResponseEntity.ok(bandService.getBand(bandId));
    }

    @PutMapping("/{bandId}")
    public ResponseEntity<String> updateBand(@PathVariable String bandId,
                                             @RequestBody BandUpdateRequest request) {
        bandService.updateBand(bandId, request);
        return ResponseEntity.ok("밴드 정보가 수정되었습니다.");
    }

    @DeleteMapping("/{bandId}")
    public ResponseEntity<String> deleteBand(@PathVariable String bandId) {
        bandService.deleteBand(bandId);
        return ResponseEntity.ok("밴드가 삭제되었습니다.");
    }

    @GetMapping("/searchAllFilter")
    public ResponseEntity<List<BandResponse>> searchBands(
            @RequestParam(required = false) BandPosition position,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) BandCategory category
    ) {
        return ResponseEntity.ok(bandService.searchBandsPRC(position, region, category));
    }

    @GetMapping("/searchByPositionAndRegion")
    public ResponseEntity<List<BandResponse>> searchBands(
            @RequestParam(required = false) BandPosition position,
            @RequestParam(required = false) String region
    ) {
        return ResponseEntity.ok(bandService.searchBandsPR(position, region));
    }
}