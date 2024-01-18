package chbaly.adil.taxetnbservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaxeResponse {
    private String label;
    private String description;
    private Long terrain_id;
    private LocalDateTime dueTime;
}
