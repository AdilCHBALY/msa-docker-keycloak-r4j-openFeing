package chbaly.adil.tauxtnbservice.repository;

import chbaly.adil.tauxtnbservice.entity.Taux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TauxRepository extends JpaRepository<Taux,Long> {
    @Query("SELECT taux FROM Taux taux WHERE taux.category_id=:id")
    public List<Taux> findByCategory_id(@Param("id") Long id);
}
