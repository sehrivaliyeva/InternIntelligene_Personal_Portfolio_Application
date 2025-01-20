package portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PortfolioRequest {

    @NotBlank(message = "Projects cannot be empty")
    @Size(min = 5, max = 100, message = "Projects must be between 5 and 100 characters")
    private String projects;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Skills cannot be empty")
    private String skills;

    @NotBlank(message = "Experience cannot be empty")
    private String experience;

    @NotBlank(message = "Education cannot be empty")
    private String education;

    @NotNull(message = "User ID cannot be null")
    private Long userId;


}
