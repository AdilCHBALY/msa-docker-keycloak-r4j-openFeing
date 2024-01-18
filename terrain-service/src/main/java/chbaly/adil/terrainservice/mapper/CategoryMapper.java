package chbaly.adil.terrainservice.mapper;

import chbaly.adil.terrainservice.dto.Request.CategoryRequest;
import chbaly.adil.terrainservice.dto.Response.CategoryResponse;
import chbaly.adil.terrainservice.dto.Response.TauxResponse;
import chbaly.adil.terrainservice.dto.Taux;
import chbaly.adil.terrainservice.entities.Category;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class CategoryMapper {


    public static CategoryResponse mapToResponse(Category category, List<Taux> byCategory) {
        TauxResponse tauxResponse = null;
        if(!byCategory.isEmpty()){
            if(byCategory.size()>=2){
                byCategory.sort(Comparator.comparing(Taux::getCreatedAt).reversed());
                Taux latestTaux = byCategory.get(0);
                boolean augmented = byCategory.get(0).getValue() > byCategory.get(1).getValue();
                tauxResponse = TauxResponse.builder()
                        .id(latestTaux.getId())
                        .taux(latestTaux.getValue())
                        .augmented(augmented)
                        .build();
            }else{
                tauxResponse = TauxResponse.builder()
                        .taux(byCategory.get(0).getValue())
                        .augmented(true)
                        .id(byCategory.get(0).getId())
                        .build();
            }
        }
        return CategoryResponse.builder()
                .id(category.getId())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .libelle(category.getLibelle())
                .taux(tauxResponse)
                .build();
    }
}
