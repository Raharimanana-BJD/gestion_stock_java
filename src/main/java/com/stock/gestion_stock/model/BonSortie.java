package com.stock.gestion_stock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bons_sortie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonSortie {
    @Id
    private String numBonSortie;
    private String numProduit;
    private Integer qteSortie;
    private String dateSortie; // Conservé en String selon l'énoncé
}