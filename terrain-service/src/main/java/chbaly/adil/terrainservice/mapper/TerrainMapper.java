package chbaly.adil.terrainservice.mapper;

import chbaly.adil.terrainservice.dto.Request.TerrainRequest;
import chbaly.adil.terrainservice.dto.Response.TerrainResponse;
import chbaly.adil.terrainservice.dto.Taux;
import chbaly.adil.terrainservice.entities.Category;
import chbaly.adil.terrainservice.entities.Terrain;
import chbaly.adil.terrainservice.model.Taxe;

import java.time.LocalDateTime;
import java.util.List;

public class TerrainMapper {


    public static Terrain mapToEntity(TerrainRequest terrain, Category category) {
        return Terrain.builder()
                .area(terrain.getArea())
                .name(terrain.getName())
                .address(terrain.getAddress())
                .createdAt(LocalDateTime.now())
                .category(category)
                .description(terrain.getDescription())
                .client_id(terrain.getClient_id())
                .build();
    }

    public static TerrainResponse mapToRespone(Terrain terrain, List<Taux> tauxes) {
        return TerrainResponse.builder()
                .id(terrain.getId())
                .area(terrain.getArea())
                .category(CategoryMapper.mapToResponse(terrain.getCategory(),tauxes))
                .name(terrain.getName())
                .address(terrain.getAddress())
                .description(terrain.getDescription())
                .build();
    }
}
