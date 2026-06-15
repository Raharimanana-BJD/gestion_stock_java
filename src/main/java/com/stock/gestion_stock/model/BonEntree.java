package com.stock.gestion_stock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bons_entree")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonEntree {
    @Id
    private String numBonEntree;
    private String numProduit;
    private Integer qteEntree;
    private String dateEntree; // Conservé en String selon l'énoncé
}