package dding.band.entity;

import dding.band.config.eums.BandCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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



    @Enumerated(EnumType.STRING)
    private BandCategory category;      // 카테고리 (팝, 락...)
    private String preferredRegion;     // 선호 지역 (ex: 서울시, 부산시)
    private String description;// 짧은 소개


    private LocalDateTime createdAt; // 생성일
    private LocalDateTime modifiedAt; // 수정일


    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BandPositionSlot> positionSlots = new ArrayList<>();

    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BandMember> members = new ArrayList<>();


}
