package com.stock.gestion_stock.controller;

import com.stock.gestion_stock.model.*;
import com.stock.gestion_stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/produits")
    public List<Produit> getAll() {
        return stockService.listerProduits();
    }

    @GetMapping("/produits/recherche")
    public List<Produit> search(@RequestParam String q) {
        return stockService.rechercherProduits(q);
    }

    @PostMapping("/produits")
    public Produit create(@RequestBody Produit p) {
        return stockService.sauvegarderProduit(p);
    }

    @DeleteMapping("/produits/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable String id) {
        stockService.supprimerProduit(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/bons-entree")
    public BonEntree createEntree(@RequestBody BonEntree bon) {
        return stockService.ajouterBonEntree(bon);
    }

    @PostMapping("/bons-sortie")
    public BonSortie createSortie(@RequestBody BonSortie bon) {
        return stockService.ajouterBonSortie(bon);
    }

    @GetMapping("/produits/{id}/mouvements")
    public List<MouvementDto> getMouvements(@PathVariable String id) {
        return stockService.obtenirMouvements(id);
    }

    @PostMapping("/produits/{id}/recalcul")
    public ResponseEntity<Void> forceRecalcul(@PathVariable String id) {
        stockService.recalculerStockForce(id);
        return ResponseEntity.ok().build();
    }
}