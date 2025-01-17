package portfolio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import portfolio.dto.PortfolioRequest;
import portfolio.dto.PortfolioResponse;
import portfolio.dto.PortfolioUpdateRequest;
import portfolio.entity.Portfolio;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {


    List<PortfolioResponse> toPortfolioResponseList(List<Portfolio> portfolioEntities);

    @Mapping(target = "user.id", source = "userId")
    Portfolio toPortfolio(PortfolioRequest portfolioRequest);

    PortfolioResponse toPortfolioResponse(Portfolio portfolio);

    void toPortfolio(PortfolioUpdateRequest portfolioRequest, @MappingTarget Portfolio portfolio);

}

