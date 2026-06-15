package com.stock.gestion_stock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MouvementDto {
    private String numBon;
    private String date;
    private Integer qteEntree;
    private Integer qteSortie;
}