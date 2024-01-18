package chbaly.adil.terrainservice.dto.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class TerrainRequest {
    private String name;
    private Double area;
    private String description;
    private String address;
    private Long category_id;
    private String client_id;
}
