package dding.band.repository;


import dding.band.entity.Band;
import dding.band.entity.BandMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BandRepository extends JpaRepository<Band, String> {
    void deleteAllByBandId(String bandId);

    Optional<Band> findByLeaderId(String leaderId);

    List<Band> findAllByMembers_UserId(String userId);



    // Band 엔티티에 대한 CRUD 메서드가 자동으로 생성됩니다.
    // 추가적인 쿼리 메서드를 정의할 수 있습니다.
}
