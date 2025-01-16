package portfolio.repository;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(@NotNull(message = "Username must not be null")
                             @Size(min = 6, max = 50, message = "Username must be between 3 and 50 characters")
                             String username);

    Optional<User> findByUsername(@NotNull(message = "Username must not be null")
                                  String username);
}
