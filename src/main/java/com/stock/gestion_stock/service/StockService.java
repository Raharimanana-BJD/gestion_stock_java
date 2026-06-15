package com.stock.gestion_stock.service;

import com.stock.gestion_stock.model.*;
import com.stock.gestion_stock.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final ProduitRepository produitRepository;
    private final BonEntreeRepository bonEntreeRepository;
    private final BonSortieRepository bonSortieRepository;

    // --- PRODUITS ---
    public List<Produit> listerProduits() {
        return produitRepository.findAll();
    }

    public List<Produit> rechercherProduits(String keyword) {
        return produitRepository.findByNumProduitContainingOrDesignContainingIgnoreCase(keyword, keyword);
    }

    public Produit sauvegarderProduit(Produit p) {
        return produitRepository.save(p);
    }

    public void supprimerProduit(String id) {
        produitRepository.deleteById(id);
    }

    // --- BON D'ENTREE ---
    @Transactional
    public BonEntree ajouterBonEntree(BonEntree bon) {
        BonEntree sauve = bonEntreeRepository.save(bon);
        produitRepository.incrementerStock(bon.getNumProduit(), bon.getQteEntree());
        return sauve;
    }

    @Transactional
    public void supprimerBonEntree(String id) {
        bonEntreeRepository.findById(id).ifPresent(bon -> {
            produitRepository.decrementerStock(bon.getNumProduit(), bon.getQteEntree());
            bonEntreeRepository.deleteById(id);
        });
    }

    // --- BON DE SORTIE ---
    @Transactional
    public BonSortie ajouterBonSortie(BonSortie bon) {
        BonSortie sauve = bonSortieRepository.save(bon);
        produitRepository.decrementerStock(bon.getNumProduit(), bon.getQteSortie());
        return sauve;
    }

    @Transactional
    public void supprimerBonSortie(String id) {
        bonSortieRepository.findById(id).ifPresent(bon -> {
            produitRepository.incrementerStock(bon.getNumProduit(), bon.getQteSortie());
            bonSortieRepository.deleteById(id);
        });
    }

    // --- MOUVEMENTS ET RECALCUL ---
    public List<MouvementDto> obtenirMouvements(String numProduit) {
        List<MouvementDto> mouvements = new ArrayList<>();

        bonEntreeRepository.findByNumProduit(numProduit).forEach(
                b -> mouvements.add(new MouvementDto(b.getNumBonEntree(), b.getDateEntree(), b.getQteEntree(), 0)));

        bonSortieRepository.findByNumProduit(numProduit).forEach(
                b -> mouvements.add(new MouvementDto(b.getNumBonSortie(), b.getDateSortie(), 0, b.getQteSortie())));

        return mouvements;
    }

    @Transactional
    public void recalculerStockForce(String numProduit) {
        Produit produit = produitRepository.findById(numProduit).orElseThrow();
        int entrees = bonEntreeRepository.findByNumProduit(numProduit).stream().mapToInt(BonEntree::getQteEntree).sum();
        int sorties = bonSortieRepository.findByNumProduit(numProduit).stream().mapToInt(BonSortie::getQteSortie).sum();
        produit.setStock(entrees - sorties);
        produitRepository.save(produit);
    }
}