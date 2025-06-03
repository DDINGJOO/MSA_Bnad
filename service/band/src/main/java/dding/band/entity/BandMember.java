package dding.band.entity;


import dding.band.config.eums.BandPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "band_member")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BandMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    private BandPosition position;

    private String description;//짧은 소개
    private boolean isConfirmed;//지원가 구분


    private LocalDateTime confirmedAt;
    private LocalDateTime joinedAt;


    public Boolean getIsConfirmed() {
        return isConfirmed;
    }
    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

}
