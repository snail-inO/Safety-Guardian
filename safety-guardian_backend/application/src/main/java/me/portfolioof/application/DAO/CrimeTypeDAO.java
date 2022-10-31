package me.portfolioof.application.DAO;

import me.portfolioof.application.entity.CrimeType;
import org.springframework.data.repository.CrudRepository;

public interface CrimeTypeDAO extends CrudRepository<CrimeType, Long> {
}
