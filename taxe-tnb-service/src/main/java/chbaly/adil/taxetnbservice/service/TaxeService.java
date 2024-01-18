package chbaly.adil.taxetnbservice.service;

import chbaly.adil.taxetnbservice.dto.TaxeDTO;
import chbaly.adil.taxetnbservice.dto.TaxeResponse;
import chbaly.adil.taxetnbservice.entities.Taxe;

import java.util.List;

public interface TaxeService {
    List<TaxeDTO> getAll();
    List<TaxeDTO> getTaxesByTerrain(Long id);
    void addTaxeToTerrain(TaxeResponse taxe);
    void editTaxe(TaxeResponse taxe,Long id);
    void payTax(Long id);
    void delete(Long id);
}
