package chbaly.adil.terrainservice.dto.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TauxRequest {
    private Double taux;
    private Long category_id;
}
