package portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotNull(message = "First name must not be null")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    private String lastName;


    @NotNull(message = "Username must not be null")
    @Size(min = 6, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotNull(message = "Password must not be null")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;


    @NotNull(message = "Email must not be null")
    @Email(message = "Email should be valid")
    private String email;

    public @NotNull(message = "First name must not be null") String getFirstName() {
        return firstName;
    }


}

