package dding.band.dto.request;


import dding.band.config.eums.BandPosition;
import dding.band.entity.Band;
import dding.band.entity.BandPositionSlot;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BandPositionCountRequest {

    private BandPosition position;
    private int count;


    public static BandPositionSlot toEntity(BandPositionCountRequest req,Band band) {
        return BandPositionSlot.builder()
                .band(band)
                .position(req.getPosition())
                .requiredCount(req.getCount())
                .isCloses(false)
                .build();
    }
}
