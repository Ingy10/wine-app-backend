package com.wineapp.wine_app_backend.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WineDTO {
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
    private String tastingNotes;
    private String characteristics;

    private String servingTemp;
    private String foodPairings;
}
