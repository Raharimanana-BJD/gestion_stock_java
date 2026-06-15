package com.stock.gestion_stock.repository;

import com.stock.gestion_stock.model.BonSortie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BonSortieRepository extends JpaRepository<BonSortie, String> {
    List<BonSortie> findByNumProduit(String numProduit);
}