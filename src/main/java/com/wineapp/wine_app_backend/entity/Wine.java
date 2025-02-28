package com.wineapp.wine_app_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "wines")
@Data
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wineName;
    private String winery;
    private String vintage;
    private String wineStyle;
    private String varietal;
    private String region;
    private String subRegion;
    private String country;
    private String provinceState;
    private BigDecimal price;
    
    // Wine characteristics
    private String body;
    private String sugar;
    private BigDecimal alcoholContent;
    private String acidity;
    private String tannins;
    
    // Tasting notes
    private String tastingNote1;
    private String tastingNote2;
    private String tastingNote3;
    private String tastingNote4;
    private String tastingNote5;
    
    private String servingTemp;
    private String foodPairings; // Comma-separated list of food pairings
}
