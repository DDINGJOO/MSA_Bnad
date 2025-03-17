package dding.band.dto.response;


import dding.band.config.eums.BandCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BandResponse {
    private String bandId;
    private String leaderId;
    private String bandName;
    private String bandThumbnail;
    private BandCategory category;
    private String preferredRegion;
    private String comment;
    private boolean checkFullBang;

}
