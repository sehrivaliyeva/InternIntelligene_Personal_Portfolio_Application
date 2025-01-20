package portfolio.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import portfolio.dto.PortfolioRequest;
import portfolio.dto.PortfolioResponse;
import portfolio.dto.PortfolioUpdateRequest;
import portfolio.entity.Portfolio;
import portfolio.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T01:33:06+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PortfolioMapperImpl implements PortfolioMapper {

    @Override
    public Portfolio toPortfolio(PortfolioRequest portfolioRequest) {
        if ( portfolioRequest == null ) {
            return null;
        }

        Portfolio portfolio = new Portfolio();

        portfolio.setUser( portfolioRequestToUser( portfolioRequest ) );
        portfolio.setUsername( portfolioRequest.getUsername() );
        portfolio.setProjects( portfolioRequest.getProjects() );
        portfolio.setSkills( portfolioRequest.getSkills() );
        portfolio.setExperience( portfolioRequest.getExperience() );
        portfolio.setEducation( portfolioRequest.getEducation() );

        return portfolio;
    }

    @Override
    public PortfolioResponse toPortfolioResponse(Portfolio portfolio) {
        if ( portfolio == null ) {
            return null;
        }

        PortfolioResponse portfolioResponse = new PortfolioResponse();

        portfolioResponse.setProjects( portfolio.getProjects() );
        portfolioResponse.setSkills( portfolio.getSkills() );
        portfolioResponse.setExperience( portfolio.getExperience() );
        portfolioResponse.setEducation( portfolio.getEducation() );

        return portfolioResponse;
    }

    @Override
    public void toPortfolio(PortfolioUpdateRequest portfolioRequest, Portfolio portfolio) {
        if ( portfolioRequest == null ) {
            return;
        }

        portfolio.setProjects( portfolioRequest.getProjects() );
        portfolio.setSkills( portfolioRequest.getSkills() );
        portfolio.setExperience( portfolioRequest.getExperience() );
        portfolio.setEducation( portfolioRequest.getEducation() );
    }

    protected User portfolioRequestToUser(PortfolioRequest portfolioRequest) {
        if ( portfolioRequest == null ) {
            return null;
        }

        User user = new User();

        user.setId( portfolioRequest.getUserId() );

        return user;
    }
}
