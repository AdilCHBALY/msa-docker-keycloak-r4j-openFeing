package chbaly.adil.taxetnbservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Terrain {
    private Long id;
    private String name;
    private Double area;
    private String address;
    private String description;
    private Category category;
}
