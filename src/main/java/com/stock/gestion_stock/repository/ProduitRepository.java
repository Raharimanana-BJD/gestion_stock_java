package com.stock.gestion_stock.repository;

import com.stock.gestion_stock.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, String> {

    List<Produit> findByNumProduitContainingOrDesignContainingIgnoreCase(String numProduit, String design);

    @Modifying
    @Query("UPDATE Produit p SET p.stock = p.stock + :qte WHERE p.numProduit = :id")
    void incrementerStock(@Param("id") String numProduit, @Param("qte") Integer qte);

    @Modifying
    @Query("UPDATE Produit p SET p.stock = p.stock - :qte WHERE p.numProduit = :id")
    void decrementerStock(@Param("id") String numProduit, @Param("qte") Integer qte);
}