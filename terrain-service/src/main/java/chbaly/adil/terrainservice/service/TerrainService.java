package chbaly.adil.terrainservice.service;

import chbaly.adil.terrainservice.dto.Request.TerrainRequest;
import chbaly.adil.terrainservice.dto.Response.TerrainResponse;

import java.util.List;

public interface TerrainService {
    List<TerrainResponse> getAllTerrains();
    TerrainResponse getTerrainById(Long id);
    void createTerrain(TerrainRequest terrain);
    void updateTerrain(TerrainRequest terrainRequest,Long id);
    void deleteTerrain(Long id);
    List<TerrainResponse> getTerrainByClient(String id);
}
