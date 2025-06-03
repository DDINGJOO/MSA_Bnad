package dding.band.dto.request;


import dding.band.config.eums.BandPosition;
import dding.band.entity.Band;
import dding.band.entity.BandMember;
import dding.band.repository.BandRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class BandMemberJoinRequest {

    private String userId;

    private String bandId;

    private BandPosition position;

    private String description;//짧은 소개



    public BandMember toEntity(BandRepository bandRepository) {
        // BandRepository를 사용하여 bandId로 Band 객체를 조회합니다.
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new IllegalArgumentException("Band not found with id: " + bandId));
        return BandMember.builder()
                .band(band)
                .userId(userId)
                .position(position)
                .description(description)
                .isConfirmed(false)
                .confirmedAt(null)
                .joinedAt(LocalDateTime.now())
                .build();
    }



}
