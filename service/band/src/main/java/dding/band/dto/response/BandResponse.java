package dding.band.dto.response;


import dding.band.config.eums.BandCategory;
import dding.band.entity.Band;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class BandResponse {

    private String id;
    private String name;
    private String leaderId;
    private BandCategory category;
    private Integer count;
    private String description;


    private List<BandPositionSlotResponse> positions; // 포지션별 인원 수
    private List<BandMemberResponse> members; // 가입된 멤버 목록
    private String preferredRegion; // 선호 지역 (ex: 서울시, 부산시)
    private Integer currentMemberCount;// 현재 멤버 수


    public static BandResponse from(Band band) {
        return BandResponse.builder()
                .id(band.getBandId())
                .name(band.getBandName())
                .category(band.getCategory())
                .description(band.getDescription())
                .positions(band.getPositionSlots()
                        .stream()
                        .map(BandPositionSlotResponse::from)
                        .collect(Collectors.toList()))
                .members(band.getMembers()
                        .stream()
                        .map(BandMemberResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }
}

