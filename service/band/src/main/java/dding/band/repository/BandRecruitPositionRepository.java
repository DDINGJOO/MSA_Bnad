package dding.band.repository;

import dding.band.config.eums.BandPosition;
import dding.band.entity.BandRecruitPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BandRecruitPositionRepository  extends JpaRepository<BandRecruitPosition , Long> {
    List<BandRecruitPosition> findByBandId(String bandId);
    List<BandRecruitPosition> findByBandIdAndIsClosedFalse(String bandId);
    boolean existsByBandIdAndPosition(String bandId, BandPosition position);
}
