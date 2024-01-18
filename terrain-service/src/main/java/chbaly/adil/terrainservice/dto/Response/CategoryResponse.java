package chbaly.adil.terrainservice.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String libelle;
    private String description;
    private LocalDateTime createdAt;
    private TauxResponse taux;
}
