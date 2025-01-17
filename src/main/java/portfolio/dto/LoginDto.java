package portfolio.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    @NotNull(message = "Username must not be null")
    private String username;
    @NotNull(message = "password must not be null")
    private String password;

}
