package chbaly.adil.taxetnbservice.service;

import chbaly.adil.taxetnbservice.dto.TaxeDTO;
import chbaly.adil.taxetnbservice.dto.TaxeResponse;
import chbaly.adil.taxetnbservice.entities.Taxe;
import chbaly.adil.taxetnbservice.external.TerrainRestClient;
import chbaly.adil.taxetnbservice.mapper.TaxeMapper;
import chbaly.adil.taxetnbservice.model.PayementType;
import chbaly.adil.taxetnbservice.model.Taux;
import chbaly.adil.taxetnbservice.model.Terrain;
import chbaly.adil.taxetnbservice.repository.TaxeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TaxeServiceImpl implements TaxeService{
    private final TaxeRepository taxeRepository;
    private final TerrainRestClient terrainRestClient;

    public TaxeServiceImpl(TaxeRepository taxeRepository, TerrainRestClient terrainRestClient) {
        this.taxeRepository = taxeRepository;
        this.terrainRestClient = terrainRestClient;
    }

    @Override
    public List<TaxeDTO> getAll() {
        List<Taxe> taxes = taxeRepository.findAll();
        taxes.forEach(taxe -> {
            if (taxe.getDueTime().isBefore(LocalDateTime.now())) {
                taxe.setPayementType(PayementType.LATE);
            }
            taxeRepository.save(taxe);
        });

        return taxes.stream().map(taxe -> TaxeMapper.mapToModel(taxe,terrainRestClient.getTerrainById(taxe.getTerrain_id()))).toList();
    }

    @Override
    public List<TaxeDTO> getTaxesByTerrain(Long id) {
        List<Taxe> taxes = taxeRepository.getTaxeByTerrain_id(id);
        taxes.forEach(taxe -> {
            if (taxe.getDueTime().isBefore(LocalDateTime.now())) {
                taxe.setPayementType(PayementType.LATE);
            }
            taxeRepository.save(taxe);
        });
        return taxes.stream().map(taxe -> TaxeMapper.mapToModel(taxe,terrainRestClient.getTerrainById(taxe.getTerrain_id()))).toList();
    }

    @Override
    public void addTaxeToTerrain(TaxeResponse taxeResponse) {
        Terrain terrain = terrainRestClient.getTerrainById(taxeResponse.getTerrain_id());
        Taxe taxe = Taxe.builder()
                .terrain_id(taxeResponse.getTerrain_id())
                .payementType(PayementType.NOT_PAYED)
                .label(taxeResponse.getLabel())
                .amount(terrain.getArea()*terrain.getCategory().getTaux().getTaux())
                .dueTime(taxeResponse.getDueTime())
                .description(taxeResponse.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
        taxeRepository.save(taxe);
    }

    @Override
    public void editTaxe(TaxeResponse taxeResponse, Long id) {
        Taxe taxe = taxeRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid taxe ID"));
        taxe.setLabel(taxeResponse.getLabel());
        taxe.setDueTime(taxeResponse.getDueTime());
        taxe.setLabel(taxeResponse.getLabel());
        taxe.setTerrain_id(taxeResponse.getTerrain_id());
        taxeRepository.save(taxe);
    }

    @Override
    public void payTax(Long id) {
        System.out.println("in Pay for : "+id);
        Taxe taxe = taxeRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid taxe ID"));
        System.out.println(taxe.getPayementType());
        if(taxe.getPayementType()==PayementType.NOT_PAYED || taxe.getPayementType()==PayementType.LATE){
            if(taxe.getPayementType()==PayementType.LATE){
                System.out.println("Late for : "+id);
                taxe.setAmount(taxe.getAmount()*0.1+taxe.getAmount());
            }
            System.out.println("Payed for : "+id);
            taxe.setPayementType(PayementType.PAYED);
            taxeRepository.save(taxe);
        }
    }

    @Override
    public void delete(Long id) {
        Taxe taxe = taxeRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid taxe ID"));
        taxeRepository.delete(taxe);
    }
}
