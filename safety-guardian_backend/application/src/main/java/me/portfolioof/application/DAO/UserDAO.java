package me.portfolioof.application.DAO;

import me.portfolioof.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
}
