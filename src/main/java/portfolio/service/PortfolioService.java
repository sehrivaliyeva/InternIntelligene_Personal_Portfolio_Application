package portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.dto.PortfolioRequest;
import portfolio.dto.PortfolioResponse;
import portfolio.dto.PortfolioUpdateRequest;
import portfolio.entity.Portfolio;
import portfolio.entity.User;
import portfolio.exception.NotFoundException;
import portfolio.exception.ResourceNotFoundException;
import portfolio.mapper.PortfolioMapper;
import portfolio.repository.PortfolioRepository;
import portfolio.repository.UserRepository;

import java.util.Optional;

@Service
public class PortfolioService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PortfolioMapper portfolioMapper;

    public PortfolioResponse createPortfolioForUser(String username, PortfolioRequest portfolioRequest) {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if (portfolioRepository.existsByUser(user)) {
            throw new RuntimeException("User already has a portfolio");
        }

        portfolioRequest.setUserId(user.get().getId());

        Portfolio portfolio = portfolioMapper.toPortfolio(portfolioRequest);

        portfolioRepository.save(portfolio);
        PortfolioResponse response = portfolioMapper.toPortfolioResponse(portfolio);
        return response;
    }

    public PortfolioResponse updatePortfolio(Long id, PortfolioUpdateRequest portfolioRequest) {

        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id: " + id));

        User user = userRepository.findById(portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found "));
        portfolio.setUser(user);
        portfolioMapper.toPortfolio(portfolioRequest, portfolio);
        portfolioRepository.save(portfolio);
        return portfolioMapper.toPortfolioResponse(portfolio);
    }


    public PortfolioResponse getPortfolioByUsername(String username) {
        Portfolio portfolio = portfolioRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Portfolio not found for username: " + username));
        return portfolioMapper.toPortfolioResponse(portfolio);
    }


    public void deletePortfolioById(Long id) {
        portfolioRepository.deleteById(id);
    }
}
