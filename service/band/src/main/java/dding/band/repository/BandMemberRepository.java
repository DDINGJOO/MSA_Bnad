package dding.band.repository;


import dding.band.entity.BandMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandMemberRepository extends JpaRepository<BandMember ,Long> {
    List<BandMember> findByBandId(String bandId);
    List<BandMember> findByUserId(String userId);
    boolean existsByBandIdAndUserId(String bandId, String userId);
}
