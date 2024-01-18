package chbaly.adil.terrainservice.repository;


import chbaly.adil.terrainservice.entities.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain,Long> {
    @Query("SELECT terrain FROM Terrain terrain WHERE terrain.client_id=:id")
    public List<Terrain> getTerrainsByClient_id(@Param("id") String id);
}
