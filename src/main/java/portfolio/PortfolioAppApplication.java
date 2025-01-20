package portfolio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "PERSONAL PORTFOLIO",
                description = "Backend Rest APIs for Portfolio"
        )
)
public class PortfolioAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioAppApplication.class, args);
    }

}
