package chbaly.adil.terrainservice;

import chbaly.adil.terrainservice.entities.Category;
import chbaly.adil.terrainservice.entities.Terrain;
import chbaly.adil.terrainservice.repository.CategoryRepository;
import chbaly.adil.terrainservice.repository.TerrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class TerrainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TerrainServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(TerrainRepository terrainRepository,CategoryRepository categoryRepository){
        return args -> {
            List<Category> categories = List.of(
                    Category.builder()
                            .libelle("Villa")
                            .id(1L)
                            .description("Villa is Cool")
                            .createdAt(LocalDateTime.now())
                            .build(),
                    Category.builder()
                            .libelle("Apartment")
                            .id(2L)
                            .description("Apartment is Cooler")
                            .createdAt(LocalDateTime.now())
                            .build()
            );
            categoryRepository.saveAll(categories);
            Terrain terrain = Terrain.builder()
                    .id(1L)
                    .client_id("649b780a-18d8-4ebe-b40b-3716ced4d9eb")
                    .description("Description 1 ")
                    .category(categories.get(0))
                    .createdAt(LocalDateTime.now())
                    .address("Hda Dar ;)")
                    .area(55D)
                    .name("Hay Zitoune")
                    .build();
            terrainRepository.save(terrain);
        };
    }
}
