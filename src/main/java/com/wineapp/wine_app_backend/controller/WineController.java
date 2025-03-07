package com.wineapp.wine_app_backend.controller;

import com.wineapp.wine_app_backend.dto.WineDTO;
import com.wineapp.wine_app_backend.service.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(WineController.class);
    
    private final WineService wineService;

    @Autowired
    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    public ResponseEntity<List<WineDTO>> getAllWines() {
        logger.info("[WINE CONTROLLER] GET request received for all wines");
        List<WineDTO> wines = wineService.getAllWines();
        logger.info("Returning {} wines", wines.size());
        return ResponseEntity.ok(wines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WineDTO> getWineById(@PathVariable Long id) {
        Optional<WineDTO> wine = wineService.getWineById(id);
        return wine.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WineDTO> createWine(@RequestBody WineDTO wineDTO) {
        logger.info("POST request received to create a new wine: {}", wineDTO.getWineName());
        WineDTO createdWine = wineService.saveWine(wineDTO);
        logger.info("Wine created successfully: {}", createdWine);
        return new ResponseEntity<>(createdWine, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WineDTO> updateWine(@PathVariable Long id, @RequestBody WineDTO wineDTO) {
        logger.info("PUT request received to update wine with id: {}", id);
        Optional<WineDTO> existingWine = wineService.getWineById(id);
        if (existingWine.isPresent()) {
            wineDTO.setId(id);
            WineDTO updatedWine = wineService.saveWine(wineDTO);
            logger.info("Wine updated successfully with id: {}", id);
            return ResponseEntity.ok(updatedWine);
        } else {
            logger.warn("Cannot update - wine not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWine(@PathVariable Long id) {
        logger.info("DELETE request received for wine with id: {}", id);
        Optional<WineDTO> existingWine = wineService.getWineById(id);
        if (existingWine.isPresent()) {
            wineService.deleteWine(id);
            logger.info("Wine deleted successfully with id: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Cannot delete - wine not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
