package dding.band.dto.response;


import dding.band.config.eums.BandPosition;
import dding.band.entity.BandMember;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BandMemberResponse {

    private String userId;
    private BandPosition position;
    private String description;
    private LocalDateTime confirmedAt;

    public static BandMemberResponse from(BandMember member) {
        return BandMemberResponse.builder()
                .userId(member.getUserId())
                .position(member.getPosition())
                .description(member.getDescription())
                .confirmedAt(member.getConfirmedAt())
                .build();
    }
}


