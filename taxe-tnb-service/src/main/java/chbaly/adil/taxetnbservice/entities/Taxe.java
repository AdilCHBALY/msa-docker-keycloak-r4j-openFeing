package chbaly.adil.taxetnbservice.entities;


import chbaly.adil.taxetnbservice.model.PayementType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Taxe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;
    private String description;
    private Double amount;
    @Enumerated(EnumType.ORDINAL)
    private PayementType payementType;
    private Long terrain_id;
    @JsonFormat(timezone = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dueTime;
    @JsonFormat(timezone = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdAt;
}
