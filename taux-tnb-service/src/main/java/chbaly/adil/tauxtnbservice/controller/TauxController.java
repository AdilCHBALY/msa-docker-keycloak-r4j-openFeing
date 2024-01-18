package chbaly.adil.tauxtnbservice.controller;


import chbaly.adil.tauxtnbservice.dto.TauxRequest;
import chbaly.adil.tauxtnbservice.entity.Taux;
import chbaly.adil.tauxtnbservice.service.TauxServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taux")
public class TauxController {
    private final TauxServiceImpl tauxService;

    public TauxController(TauxServiceImpl tauxService) {
        this.tauxService = tauxService;
    }


    @GetMapping
    public List<Taux> getAll(){return tauxService.getAll();}

    @GetMapping("/{id}")
    public List<Taux> tauxByCategoryId(@PathVariable Long id) {
        return tauxService.tauxByCategoryId(id);
    }

    @PostMapping
    public void saveTaux(@RequestBody TauxRequest taux) {
        tauxService.saveTaux(taux);
    }
}
