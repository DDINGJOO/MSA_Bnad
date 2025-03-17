package dding.band.service;


import dding.band.config.eums.BandCategory;
import dding.band.config.eums.BandPosition;
import dding.band.dto.request.BandCreateRequest;
import dding.band.dto.request.BandUpdateRequest;
import dding.band.dto.response.BandResponse;
import dding.band.entity.Band;
import dding.band.repository.BandRecruitPositionRepository;
import dding.band.repository.BandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BandService {

    private final BandRepository bandRepository;

    public String createBand(BandCreateRequest request) {
        Band band = Band.builder()
                .bandId(generateBandId())
                .leaderId(request.getLeaderId())
                .bandName(request.getBandName())
                .bandThumbnail(request.getBandThumbnail())
                .category(request.getCategory())
                .preferredRegion(request.getPreferredRegion())
                .comment(request.getComment())
                .checkFullBang(false)
                .build();

        bandRepository.save(band);
        return band.getBandId();
    }

    public List<BandResponse> searchBandsPRC(BandPosition position, String region, BandCategory category) {
        List<Band> bands = bandRepository.searchBandsByAllFilters(position, region, category);
        return bands.stream()
                .map(b -> BandResponse.builder()
                        .bandId(b.getBandId())
                        .leaderId(b.getLeaderId())
                        .bandName(b.getBandName())
                        .bandThumbnail(b.getBandThumbnail())
                        .category(b.getCategory())
                        .preferredRegion(b.getPreferredRegion())
                        .comment(b.getComment())
                        .checkFullBang(b.isCheckFullBang())
                        .build())
                .collect(Collectors.toList());
    }

    public List<BandResponse> searchBandsPR(BandPosition position, String region)
    {
        List<Band> bands = bandRepository.searchBandsByPositionAndPreferredRegion(position, region);
        return bands.stream()
                .map(b -> BandResponse.builder()
                        .bandId(b.getBandId())
                        .leaderId(b.getLeaderId())
                        .bandName(b.getBandName())
                        .bandThumbnail(b.getBandThumbnail())
                        .category(b.getCategory())
                        .preferredRegion(b.getPreferredRegion())
                        .comment(b.getComment())
                        .checkFullBang(b.isCheckFullBang())
                        .build())
                .collect(Collectors.toList());
    }



    public List<BandResponse> searchBandsRC( String region, BandCategory category)
    {
        List<Band> bands = bandRepository.findAllByCategoryAndPreferredRegion( category,region);
        return bands.stream()
                .map(b -> BandResponse.builder()
                        .bandId(b.getBandId())
                        .leaderId(b.getLeaderId())
                        .bandName(b.getBandName())
                        .bandThumbnail(b.getBandThumbnail())
                        .category(b.getCategory())
                        .preferredRegion(b.getPreferredRegion())
                        .comment(b.getComment())
                        .checkFullBang(b.isCheckFullBang())
                        .build())
                .collect(Collectors.toList());
    }


    public List<BandResponse> searchBandByPosition(BandPosition position)
    {
        List<Band> bands = bandRepository.searchBandsByPosition(position);
        return bands.stream()
                .map(b -> BandResponse.builder()
                        .bandId(b.getBandId())
                        .leaderId(b.getLeaderId())
                        .bandName(b.getBandName())
                        .bandThumbnail(b.getBandThumbnail())
                        .category(b.getCategory())
                        .preferredRegion(b.getPreferredRegion())
                        .comment(b.getComment())
                        .checkFullBang(b.isCheckFullBang())
                        .build())
                .collect(Collectors.toList());
    }


    public List<BandResponse> searchBandsByCategory(BandCategory category)
    {
        List<Band> bands = bandRepository.findAllByCategory(category);
        return bands.stream()
                .map(b -> BandResponse.builder()
                        .bandId(b.getBandId())
                        .leaderId(b.getLeaderId())
                        .bandName(b.getBandName())
                        .bandThumbnail(b.getBandThumbnail())
                        .category(b.getCategory())
                        .preferredRegion(b.getPreferredRegion())
                        .comment(b.getComment())
                        .checkFullBang(b.isCheckFullBang())
                        .build())
                .collect(Collectors.toList());
    }


    public List<BandResponse> searchBandsByRegion(String region)
    {
        List<Band> bands = bandRepository.findAllByPreferredRegion(region);
        return bands.stream()
                .map(b -> BandResponse.builder()
                        .bandId(b.getBandId())
                        .leaderId(b.getLeaderId())
                        .bandName(b.getBandName())
                        .bandThumbnail(b.getBandThumbnail())
                        .category(b.getCategory())
                        .preferredRegion(b.getPreferredRegion())
                        .comment(b.getComment())
                        .checkFullBang(b.isCheckFullBang())
                        .build())
                .collect(Collectors.toList());
    }



    public BandResponse getBand(String bandId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new IllegalArgumentException("밴드를 찾을 수 없습니다."));
        return BandResponse.builder()
                .bandId(band.getBandId())
                .leaderId(band.getLeaderId())
                .bandName(band.getBandName())
                .bandThumbnail(band.getBandThumbnail())
                .category(band.getCategory())
                .preferredRegion(band.getPreferredRegion())
                .comment(band.getComment())
                .checkFullBang(band.isCheckFullBang())
                .build();
    }

    public void updateBand(String bandId, BandUpdateRequest request) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new IllegalArgumentException("밴드를 찾을 수 없습니다."));
        band.setBandName(request.getBandName());
        band.setBandThumbnail(request.getBandThumbnail());
        band.setCategory(request.getCategory());
        band.setPreferredRegion(request.getPreferredRegion());
        band.setComment(request.getComment());
        band.setCheckFullBang(request.isCheckFullBang());
        bandRepository.save(band);
    }

    public void deleteBand(String bandId) {
        bandRepository.deleteById(bandId);
    }

    private String generateBandId() {
        return "BND-" + UUID.randomUUID(); // 예시 ( UUID/ULID )
    }
}