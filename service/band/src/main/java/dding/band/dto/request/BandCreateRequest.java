package dding.band.dto.request;

import dding.band.config.eums.BandCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BandCreateRequest {
    private String leaderId;
    private String bandName;
    private String bandThumbnail;
    private BandCategory category;
    private String preferredRegion;
    private String comment;
}