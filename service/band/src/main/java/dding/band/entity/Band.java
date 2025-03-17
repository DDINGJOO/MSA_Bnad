package dding.band.entity;

import dding.band.config.eums.BandCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "band")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Band {

    @Id
    private String bandId;              // UUID, ULID 등

    private String leaderId;            // 리더 유저 ID
    private String bandName;            // 밴드 이름
    private String bandThumbnail;       // 썸네일 이미지 URL
    @Enumerated(EnumType.STRING)
    private BandCategory category;      // 카테고리 (팝, 락...)
    private String preferredRegion;     // 선호 지역 (ex: 서울시, 부산시)
    private String comment;             // 짧은 소개
    private boolean checkFullBang;      // 모집 마감 여부


}
