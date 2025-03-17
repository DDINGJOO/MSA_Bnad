package dding.band.dto.request;

import dding.band.config.eums.BandPosition;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BandRecruitPositionRequest {
    private String bandId;
    private BandPosition position;
    private String description;
}
