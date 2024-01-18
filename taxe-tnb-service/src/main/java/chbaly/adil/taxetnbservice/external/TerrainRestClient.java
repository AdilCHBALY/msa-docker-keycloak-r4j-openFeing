package chbaly.adil.taxetnbservice.external;


import chbaly.adil.taxetnbservice.model.Terrain;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("TERRAIN-SERVICE")
public interface TerrainRestClient {
    @GetMapping("/api/terrain/{id}")
    @CircuitBreaker(name="clientService",fallbackMethod = "getDefaultTerrain")
    public Terrain getTerrainById(@PathVariable("id") Long id);

    default Terrain getDefaultTerrain(Long id,Exception e){
        return Terrain.builder()
                .description("NA")
                .id(1L)
                .build();
    }
}
