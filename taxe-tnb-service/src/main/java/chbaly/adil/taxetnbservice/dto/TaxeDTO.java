package chbaly.adil.taxetnbservice.dto;

import chbaly.adil.taxetnbservice.model.PayementType;
import chbaly.adil.taxetnbservice.model.Terrain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class TaxeDTO {
    private Long id;
    private String label;
    private String description;
    private Double amount;
    private PayementType payementType;
    private String terrain;
    private LocalDateTime dueTime;
    private LocalDateTime createdAt;
}
