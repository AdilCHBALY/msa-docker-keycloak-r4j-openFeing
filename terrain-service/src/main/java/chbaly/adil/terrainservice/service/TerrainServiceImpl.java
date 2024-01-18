package chbaly.adil.terrainservice.service;

import chbaly.adil.terrainservice.dto.Request.TerrainRequest;
import chbaly.adil.terrainservice.dto.Response.TerrainResponse;
import chbaly.adil.terrainservice.dto.Taux;
import chbaly.adil.terrainservice.entities.Category;
import chbaly.adil.terrainservice.entities.Terrain;
import chbaly.adil.terrainservice.exception.DataNotFoundException;
import chbaly.adil.terrainservice.external.TauxRestClient;
import chbaly.adil.terrainservice.mapper.TerrainMapper;
import chbaly.adil.terrainservice.repository.CategoryRepository;
import chbaly.adil.terrainservice.repository.TerrainRepository;
import chbaly.adil.terrainservice.util.ExceptionConstant;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TerrainServiceImpl implements TerrainService{
    private final TerrainRepository terrainRepository;
    private final CategoryRepository categoryRepository;
    private final TauxRestClient tauxRestClient;

    public TerrainServiceImpl(final TerrainRepository terrainRepository, CategoryRepository categoryRepository, TauxRestClient tauxRestClient) {
        this.terrainRepository = terrainRepository;
        this.categoryRepository = categoryRepository;
        this.tauxRestClient = tauxRestClient;
    }

    @Override
    public List<TerrainResponse> getAllTerrains() {
        List<Terrain> terrains = terrainRepository.findAll();
        return terrains.stream().map(terrain -> TerrainMapper.mapToRespone(terrain,tauxRestClient.getByCategory(terrain.getCategory().getId()))).toList();
    }

    @Override
    public TerrainResponse getTerrainById(Long id) {
        Terrain terrain = terrainRepository.findById(id).orElseThrow(()->new DataNotFoundException(ExceptionConstant.TERRAIN_NOT_FOUND));
        List<Taux> tauxes = tauxRestClient.getByCategory(terrain.getCategory().getId());
        return TerrainMapper.mapToRespone(terrain,tauxes);
    }

    @Override
    public void createTerrain(TerrainRequest terrain) {
        if(terrain!=null){
            Category category = categoryRepository.findById(terrain.getCategory_id()).orElseThrow(()->new DataNotFoundException(ExceptionConstant.CATEGORY_NOT_FOUND));
            terrainRepository.save(TerrainMapper.mapToEntity(terrain,category));
        }
    }

    @Override
    public void updateTerrain(TerrainRequest terrainRequest, Long id) {
       Terrain terrain = terrainRepository.findById(id).orElseThrow(()->new DataNotFoundException(ExceptionConstant.TERRAIN_NOT_FOUND));
       terrain.setDescription(terrainRequest.getDescription());
       terrain.setArea(terrainRequest.getArea());
       terrain.setAddress(terrainRequest.getAddress());
       terrain.setName(terrainRequest.getName());
       terrain.setCategory(categoryRepository.findById(terrainRequest.getCategory_id()).orElseThrow(()->new DataNotFoundException(ExceptionConstant.CATEGORY_NOT_FOUND)));
       terrainRepository.save(terrain);
    }

    @Override
    public void deleteTerrain(Long id) {
        Terrain terrain = terrainRepository.findById(id).orElseThrow(()->new DataNotFoundException(ExceptionConstant.TERRAIN_NOT_FOUND));
        terrainRepository.delete(terrain);
    }

    @Override
    public List<TerrainResponse> getTerrainByClient(String id) {
        List<Terrain> terrains = terrainRepository.getTerrainsByClient_id(id);
        return terrains.stream().map(terrain -> TerrainMapper.mapToRespone(terrain,tauxRestClient.getByCategory(terrain.getCategory().getId()))).toList();
    }
}
