package me.portfolioof.application.DAO;

import me.portfolioof.application.entity.CrimeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrimeDataDAO extends JpaRepository<CrimeData, Long> {
    @Query(value = "SELECT * FROM crime_data WHERE ((x - ?1) ^ 2 + (y - ?2) ^ 2) <= ?3 ^ 2", nativeQuery = true)
    List<CrimeData> findByDistance(double x, double y, double range);
}
