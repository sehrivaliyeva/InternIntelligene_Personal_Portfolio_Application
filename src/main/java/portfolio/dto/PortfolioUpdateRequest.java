package portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PortfolioUpdateRequest {

    @NotBlank(message = "Projects cannot be empty")
    @Size(min = 5, max = 100, message = "Projects must be between 5 and 100 characters")
    private String projects;

    @NotBlank(message = "Skills cannot be empty")
    private String skills;

    @NotBlank(message = "Experience cannot be empty")
    private String experience;

    @NotBlank(message = "Education cannot be empty")
    private String education;

}
