package dding.band.repository;


import dding.band.config.eums.BandPosition;
import dding.band.entity.Band;
import dding.band.entity.BandPositionSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandPositionSlotRepository extends JpaRepository<BandPositionSlot,Long>
{
    BandPositionSlot findByBandAndPosition(Band band, BandPosition position);


    // BandPositionSlot 엔티티에 대한 CRUD 메서드가 자동으로 생성됩니다.
    // 추가적인 쿼리 메서드를 정의할 수 있습니다.
}
