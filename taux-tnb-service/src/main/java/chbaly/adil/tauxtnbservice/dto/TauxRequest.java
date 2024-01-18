package chbaly.adil.tauxtnbservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TauxRequest {
    private Double value;
    private Long category_id;
}
