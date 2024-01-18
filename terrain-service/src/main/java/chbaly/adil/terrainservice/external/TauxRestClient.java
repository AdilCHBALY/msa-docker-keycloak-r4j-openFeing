package chbaly.adil.terrainservice.external;


import chbaly.adil.terrainservice.dto.Request.TauxRequest;
import chbaly.adil.terrainservice.dto.Taux;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("TAUX-SERVICE")
public interface TauxRestClient {
    @GetMapping("/api/taux/{id}")
    @CircuitBreaker(name = "tauxService",fallbackMethod = "getTauxByCat")
    List<Taux> getByCategory(@PathVariable Long id);

    @PostMapping("/api/taux")
    void saveTaux(@RequestBody Taux taux);

    default List<Taux> getTauxByCat(Long id,Exception e){
        return List.of();
    }
}
