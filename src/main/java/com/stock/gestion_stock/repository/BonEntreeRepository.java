package com.stock.gestion_stock.repository;

import com.stock.gestion_stock.model.BonEntree;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BonEntreeRepository extends JpaRepository<BonEntree, String> {
    List<BonEntree> findByNumProduit(String numProduit);
}