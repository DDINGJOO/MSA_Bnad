package dding.band.repository;


import dding.band.entity.Band;
import dding.band.entity.BandMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BandMemberRepository extends JpaRepository<BandMember,Long> {
    void deleteAllByBand(Band band);

    BandMember findByBandAndUserId(Band band, String userId);

    Page<BandMember> findAllByBand(Band band, Pageable pageable);

    Page<BandMember> findAllByBandAndIsConfirmed(Band band, boolean isConfirmed, Pageable pageable);

    void deleteByUserIdAndBand(String userId, Band band);



    // BandMember 엔티티에 대한 CRUD 메서드가 자동으로 생성됩니다.
    // 추가적인 쿼리 메서드를 정의할 수 있습니다.
}
