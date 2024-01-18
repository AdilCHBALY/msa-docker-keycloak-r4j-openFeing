package chbaly.adil.terrainservice.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TauxResponse {
    private Long id;
    private Double taux;
    private Boolean augmented;
}
