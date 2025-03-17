package dding.band.dto.response;

import dding.band.config.eums.BandPosition;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BandRecruitPositionResponse {
    private Long id;
    private BandPosition position;
    private boolean isClosed;
    private String description;
}