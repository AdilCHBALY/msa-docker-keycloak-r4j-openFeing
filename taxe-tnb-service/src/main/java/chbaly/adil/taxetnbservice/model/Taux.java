package chbaly.adil.taxetnbservice.model;

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
    private Double taux;
    private Boolean augmented;
}
