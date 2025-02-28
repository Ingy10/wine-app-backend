package com.wineapp.wine_app_backend.repository;

import com.wineapp.wine_app_backend.entity.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
    
}
