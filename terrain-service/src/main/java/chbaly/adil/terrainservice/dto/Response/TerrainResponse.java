package chbaly.adil.terrainservice.dto.Response;


import chbaly.adil.terrainservice.model.Taxe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TerrainResponse {
    private Long id;
    private String name;
    private Double area;
    private String address;
    private String description;
    private CategoryResponse category;
}
