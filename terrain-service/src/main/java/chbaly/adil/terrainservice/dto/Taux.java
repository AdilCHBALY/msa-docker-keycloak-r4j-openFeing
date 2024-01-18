package chbaly.adil.terrainservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taux {
    private Long id;
    private Double value;
    private Long category_id;
    private LocalDateTime createdAt;

}
