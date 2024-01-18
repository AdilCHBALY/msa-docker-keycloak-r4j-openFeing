package chbaly.adil.taxetnbservice;

import chbaly.adil.taxetnbservice.entities.Taxe;
import chbaly.adil.taxetnbservice.external.TerrainRestClient;
import chbaly.adil.taxetnbservice.model.PayementType;
import chbaly.adil.taxetnbservice.model.Terrain;
import chbaly.adil.taxetnbservice.repository.TaxeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class TaxeTnbServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxeTnbServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(TaxeRepository taxeRepository, TerrainRestClient terrainRestClient){
        return args -> {
            List<Taxe> taxes = List.of(
                    Taxe.builder()
                            .terrain_id(1L)
                            .createdAt(LocalDateTime.now())
                            .payementType(PayementType.PAYED)
                            .label("Taxe 1")
                            .description("Desc Taxe 1")
                            .dueTime(LocalDateTime.now().minusMonths(2))
                            .amount(250D)
                            .build(),
                    Taxe.builder()
                            .terrain_id(1L)
                            .createdAt(LocalDateTime.now())
                            .payementType(PayementType.NOT_PAYED)
                            .label("Taxe 1")
                            .description("Desc Taxe 1")
                            .dueTime(LocalDateTime.now().minusMonths(2))
                            .amount(250D)
                            .build(),
                    Taxe.builder()
                            .terrain_id(1L)
                            .createdAt(LocalDateTime.now())
                            .payementType(PayementType.LATE)
                            .label("Taxe 1")
                            .description("Desc Taxe 1")
                            .dueTime(LocalDateTime.now().minusMonths(2))
                            .amount(250D)
                            .build()
            );
            taxeRepository.saveAll(taxes);
        };
    }

}
