package dding.band.entity;


import dding.band.config.eums.BandPosition;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "band_recruit_position")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BandRecruitPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bandId;  // Band 식별자 (참조용)

    @Enumerated(EnumType.STRING)
    private BandPosition position;  // 모집 포지션 (Enum)

    private boolean isClosed;       // 모집 마감 여부

    private String description;     // 포지션 설명 (선택)
}
