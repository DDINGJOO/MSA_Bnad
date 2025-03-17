package dding.band.entity;

import dding.band.config.eums.BandPosition;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "band_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BandMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bandId;  // Band 식별자

    private String userId;  // 참여 유저 ID

    @Enumerated(EnumType.STRING)
    private BandPosition position; // 실제 맡고 있는 포지션

    private LocalDateTime joinedAt;
}
