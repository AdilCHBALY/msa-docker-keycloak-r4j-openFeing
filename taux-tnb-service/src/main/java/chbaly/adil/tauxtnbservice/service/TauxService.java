package chbaly.adil.tauxtnbservice.service;

import chbaly.adil.tauxtnbservice.dto.TauxRequest;
import chbaly.adil.tauxtnbservice.entity.Taux;

import java.util.List;

public interface TauxService {
    List<Taux> tauxByCategoryId(Long id);
    void saveTaux(TauxRequest taux);
}
