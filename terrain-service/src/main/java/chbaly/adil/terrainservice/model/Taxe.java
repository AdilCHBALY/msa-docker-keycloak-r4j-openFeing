package chbaly.adil.terrainservice.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taxe {
    private Long id;
    private String label;
    private String description;
    private Double amount;
    private PayementType payementType;
    private String terrain;
    private LocalDateTime dueTime;
    private LocalDateTime createdAt;
}
