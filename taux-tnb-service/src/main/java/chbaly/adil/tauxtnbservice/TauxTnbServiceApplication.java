package chbaly.adil.tauxtnbservice;

import chbaly.adil.tauxtnbservice.entity.Taux;
import chbaly.adil.tauxtnbservice.repository.TauxRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TauxTnbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TauxTnbServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TauxRepository tauxRepository){
		return args -> {
			List<Taux> tauxes = List.of(
					Taux.builder()
							.id(1L)
							.category_id(1L)
							.createdAt(LocalDateTime.now())
							.value(10D)
							.build(),
					Taux.builder()
							.id(2L)
							.category_id(2L)
							.createdAt(LocalDateTime.now())
							.value(2D)
							.build()
			);
			tauxRepository.saveAll(tauxes);
		};
	}

}
