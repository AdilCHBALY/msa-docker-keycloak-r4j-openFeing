package chbaly.adil.taxetnbservice.controller;

import chbaly.adil.taxetnbservice.dto.TaxeDTO;
import chbaly.adil.taxetnbservice.dto.TaxeResponse;
import chbaly.adil.taxetnbservice.service.TaxeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxe")
public class TaxeController {
    private final TaxeServiceImpl taxeService;

    public TaxeController(TaxeServiceImpl taxeService) {
        this.taxeService = taxeService;
    }


    @GetMapping
    public List<TaxeDTO> getAll() {
        return taxeService.getAll();
    }

    @GetMapping("/terrain/{id}")
    public List<TaxeDTO> getTaxesByTerrain(@PathVariable Long id) {
        return taxeService.getTaxesByTerrain(id);
    }

    @PostMapping
    public void addTaxeToTerrain(@RequestBody TaxeResponse taxeResponse) {
        taxeService.addTaxeToTerrain(taxeResponse);
    }

    @PutMapping("/{id}")
    public void editTaxe(@RequestBody TaxeResponse taxeResponse,@PathVariable Long id) {
        taxeService.editTaxe(taxeResponse, id);
    }

    @GetMapping("/pay/{id}")
    public void payTax(@PathVariable Long id) {
        taxeService.payTax(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taxeService.delete(id);
    }
}
