package dding.band.dto.request;

import dding.band.config.eums.BandCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BandUpdateRequest {
    private String bandName;
    private String bandThumbnail;
    private BandCategory category;
    private String preferredRegion;
    private String comment;
    private boolean checkFullBang;
}