package dding.band.repository;

import dding.band.config.eums.BandCategory;
import dding.band.config.eums.BandPosition;
import dding.band.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository  extends JpaRepository<Band, String> {
    @Query("""
    SELECT DISTINCT b FROM Band b
    JOIN BandRecruitPosition p ON b.bandId = p.bandId
    WHERE p.isClosed = false
      AND (:position IS NULL OR p.position = :position)
      AND (:region IS NULL OR b.preferredRegion = :region)
      AND (:category IS NULL OR b.category = :category)
""")
    List<Band> searchBandsByAllFilters(@Param("position") BandPosition position,
                           @Param("region") String region,
                           @Param("category") BandCategory category);


    @Query("""
    SELECT DISTINCT b FROM Band b
    JOIN BandRecruitPosition p ON b.bandId = p.bandId
    WHERE p.isClosed = false
      AND (:position IS NULL OR p.position = :position)
      AND (:region IS NULL OR b.preferredRegion = :region)
""")
    List<Band> searchBandsByPositionAndPreferredRegion(
            @Param("position") BandPosition position,
            @Param("region") String region);

    @Query("""
    SELECT DISTINCT b FROM Band b
    JOIN BandRecruitPosition p ON b.bandId = p.bandId
    WHERE p.isClosed = false
      AND (:position IS NULL OR p.position = :position)
""")
    List<Band> searchBandsByPosition(
            @Param("position") BandPosition position);




    List<Band> findAllByPreferredRegion(String preferredRegion);

    List<Band> findAllByCategory(BandCategory category);

    List<Band> findAllByCategoryAndPreferredRegion( BandCategory category,String region);
}
