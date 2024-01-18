package chbaly.adil.taxetnbservice.mapper;

import chbaly.adil.taxetnbservice.dto.TaxeDTO;
import chbaly.adil.taxetnbservice.entities.Taxe;
import chbaly.adil.taxetnbservice.model.PayementType;
import chbaly.adil.taxetnbservice.model.Terrain;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaxeMapper {
    public static TaxeDTO mapToModel(Taxe taxe, Terrain terrain){
        return TaxeDTO.builder()
                .id(taxe.getId())
                .amount(taxe.getAmount())
                .dueTime(taxe.getDueTime())
                .label(taxe.getLabel())
                .description(taxe.getDescription())
                .createdAt(taxe.getCreatedAt())
                .payementType(taxe.getPayementType())
                .terrain(terrain.getName())
                .build();
    }
}
