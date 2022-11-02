package me.portfolioof.application.DAO;

import me.portfolioof.application.entity.CrimeData;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CrimeDataDAO extends JpaRepository<CrimeData, Long> {
    @Cacheable(value = "crime_data_list", key = "{#x, #y, #range}")
    @Query(value = "SELECT * FROM crime_data WHERE ((x - ?1) ^ 2 + (y - ?2) ^ 2) <= ?3 ^ 2", nativeQuery = true)
    Optional<List<CrimeData>> findByDistance(double x, double y, double range);
}
