package chbaly.adil.taxetnbservice.repository;

import chbaly.adil.taxetnbservice.entities.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaxeRepository extends JpaRepository<Taxe,Long> {
    @Query("SELECT taxe FROM Taxe taxe WHERE taxe.terrain_id=:terrain_id ORDER BY taxe.createdAt desc")
    List<Taxe> getTaxeByTerrain_id(@Param("terrain_id") Long terrain_id);
}
