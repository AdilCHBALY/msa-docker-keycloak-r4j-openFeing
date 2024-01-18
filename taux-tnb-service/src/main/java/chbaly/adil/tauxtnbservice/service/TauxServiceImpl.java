package chbaly.adil.tauxtnbservice.service;

import chbaly.adil.tauxtnbservice.dto.TauxRequest;
import chbaly.adil.tauxtnbservice.entity.Taux;
import chbaly.adil.tauxtnbservice.repository.TauxRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class    TauxServiceImpl implements TauxService{

    private final TauxRepository tauxRepository;

    public TauxServiceImpl(TauxRepository tauxRepository) {
        this.tauxRepository = tauxRepository;
    }

    @Override
    public List<Taux> tauxByCategoryId(Long id) {
        return tauxRepository.findByCategory_id(id);
    }

    @Override
    public void saveTaux(TauxRequest taux) {
        Taux taux1 = Taux.builder()
                .createdAt(LocalDateTime.now())
                .value(taux.getValue())
                .category_id(taux.getCategory_id())
                .build();
        tauxRepository.save(taux1);
    }

    public List<Taux> getAll() {
        return tauxRepository.findAll();
    }
}
