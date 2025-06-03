package dding.band.dto.request;


import dding.band.config.eums.BandCategory;
import dding.band.entity.Band;
import dding.band.entity.BandPositionSlot;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BandCreateRequest {
    private String bandId;
    private String name;
    private String leaderId;
    private String description;
    private String preferredRegion;     // 선호 지역 (ex: 서울시, 부산시)
    private BandCategory category;      // 카테고리 (팝, 락...)


    private List<BandPositionCountRequest>  positions;




    public static Band toBandEntity(BandCreateRequest request) {
        return Band.builder()
                .bandId(request.getBandId() == null ? UUID.randomUUID().toString() : request.getBandId())
                .bandName(request.getName())
                .category(request.getCategory())
                .leaderId(request.getLeaderId())
                .description(request.getDescription())
                .preferredRegion(request.getPreferredRegion())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }
}


