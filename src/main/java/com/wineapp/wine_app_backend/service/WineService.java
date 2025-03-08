package com.wineapp.wine_app_backend.service;

import com.wineapp.wine_app_backend.dto.WineDTO;
import com.wineapp.wine_app_backend.entity.Wine;
import com.wineapp.wine_app_backend.repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WineService {
    
    private final WineRepository wineRepository;

    @Autowired 
    public WineService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    };

    // GET list of all wines
    public List<WineDTO> getAllWines() {
        return wineRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // GET wine by ID
    public Optional<WineDTO> getWineById(Long id) {
        return wineRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    // POST/PUT wine data to repo
    public WineDTO saveWine(WineDTO wineDTO) {
        Wine wine = convertToEntity(wineDTO);
        Wine savedWine = wineRepository.save(wine);
        return convertToDTO(savedWine);
    }
    
    // delete wine by ID
    public void deleteWine(Long id) {
        wineRepository.deleteById(id);
    }

    private WineDTO convertToDTO(Wine wine) {
        WineDTO dto = new WineDTO();
        dto.setId(wine.getId());
        dto.setWineName(wine.getWineName());
        dto.setWinery(wine.getWinery());
        dto.setVintage(wine.getVintage());
        dto.setWineStyle(wine.getWineStyle());
        dto.setVarietal(wine.getVarietal());
        dto.setRegion(wine.getRegion());
        dto.setSubRegion(wine.getSubRegion());
        dto.setCountry(wine.getCountry());
        dto.setProvinceState(wine.getProvinceState());
        dto.setPrice(wine.getPrice());
        dto.setBody(wine.getBody());
        dto.setSugar(wine.getSugar());
        dto.setAlcoholContent(wine.getAlcoholContent());
        dto.setAcidity(wine.getAcidity());
        dto.setTannins(wine.getTannins());
        dto.setTastingNotes(wine.getTastingNotes());
        dto.setCharacteristics(wine.getCharacteristics());
        dto.setServingTemp(wine.getServingTemp());
        dto.setFoodPairings(wine.getFoodPairings());
        
        return dto;
    }
    
    private Wine convertToEntity(WineDTO dto) {
        Wine wine = new Wine();
        wine.setId(dto.getId());
        wine.setWineName(dto.getWineName());
        wine.setWinery(dto.getWinery());
        wine.setVintage(dto.getVintage());
        wine.setWineStyle(dto.getWineStyle());
        wine.setVarietal(dto.getVarietal());
        wine.setRegion(dto.getRegion());
        wine.setSubRegion(dto.getSubRegion());
        wine.setCountry(dto.getCountry());
        wine.setProvinceState(dto.getProvinceState());
        wine.setPrice(dto.getPrice());
        wine.setBody(dto.getBody());
        wine.setSugar(dto.getSugar());
        wine.setAlcoholContent(dto.getAlcoholContent());
        wine.setAcidity(dto.getAcidity());
        wine.setTannins(dto.getTannins());
        wine.setTastingNotes(dto.getTastingNotes());
        wine.setCharacteristics(dto.getCharacteristics());
        wine.setServingTemp(dto.getServingTemp());
        wine.setFoodPairings(dto.getFoodPairings());
        
        return wine;
    }
}
