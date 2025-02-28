package com.wineapp.wine_app_backend.controller;

import com.wineapp.wine_app_backend.dto.WineDTO;
import com.wineapp.wine_app_backend.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wines")
@CrossOrigin(origins = "*") // TODO: Restrict this in production
public class WineController {
    
    private final WineService wineService;

    @Autowired
    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    public ResponseEntity<List<WineDTO>> getAllWines() {
        return ResponseEntity.ok(wineService.getAllWines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WineDTO> getWineById(@PathVariable Long id) {
        Optional<WineDTO> wine = wineService.getWineById(id);
        return wine.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WineDTO> createWine(@RequestBody WineDTO wineDTO) {
        return new ResponseEntity<>(wineService.saveWine(wineDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WineDTO> updateWine(@PathVariable Long id, @RequestBody WineDTO wineDTO) {
        Optional<WineDTO> existingWine = wineService.getWineById(id);
        if (existingWine.isPresent()) {
            wineDTO.setId(id);
            return ResponseEntity.ok(wineService.saveWine(wineDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWine(@PathVariable Long id) {
        Optional<WineDTO> existingWine = wineService.getWineById(id);
        if (existingWine.isPresent()) {
            wineService.deleteWine(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
