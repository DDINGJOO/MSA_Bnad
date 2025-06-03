package dding.band.dto.response;

import dding.band.config.eums.BandPosition;
import dding.band.entity.BandPositionSlot;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BandPositionSlotResponse {

    private BandPosition position;
    private int requiredCount;

    public static BandPositionSlotResponse from(BandPositionSlot slot) {
        return BandPositionSlotResponse.builder()
                .position(slot.getPosition())
                .requiredCount(slot.getRequiredCount())
                .build();
    }
}

