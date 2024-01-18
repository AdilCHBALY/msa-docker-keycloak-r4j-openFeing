package chbaly.adil.terrainservice.controller;



import chbaly.adil.terrainservice.dto.Request.TerrainRequest;
import chbaly.adil.terrainservice.dto.Response.TerrainResponse;
import chbaly.adil.terrainservice.service.TerrainServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrain")
public class TerrainController {
    private final TerrainServiceImpl terrainService;

    public TerrainController(final TerrainServiceImpl terrainService) {
        this.terrainService = terrainService;
    }

    @GetMapping
    public List<TerrainResponse> getAllTerrains() {
        return terrainService.getAllTerrains();
    }

    @GetMapping("/{id}")
    public TerrainResponse getTerrainById(@PathVariable Long id) {
        return terrainService.getTerrainById(id);
    }

    @PostMapping
    public void createTerrain(@RequestBody TerrainRequest terrain) {
        terrainService.createTerrain(terrain);
    }

    @PutMapping("/{id}")
    public void updateTerrain(@RequestBody TerrainRequest terrainRequest,@PathVariable Long id) {
        terrainService.updateTerrain(terrainRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTerrain(@PathVariable Long id) {
        terrainService.deleteTerrain(id);
    }

    @GetMapping("/client/{id}")
    public List<TerrainResponse> getTerrainByClient(@PathVariable String id) {
        return terrainService.getTerrainByClient(id);
    }
}
