package portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.entity.Portfolio;
import portfolio.entity.User;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {


    boolean existsByUser(Optional<User> user);

    Optional<Portfolio> findByUsername(String username);
}
