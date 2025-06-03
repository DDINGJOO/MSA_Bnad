package dding.band.entity;


import dding.band.config.eums.BandPosition;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "band_position_slot")
public class BandPositionSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BandPosition position;

    @Column(nullable = false)
    private int requiredCount;

    private boolean isCloses;



}
