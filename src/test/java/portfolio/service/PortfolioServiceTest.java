
package portfolio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import portfolio.dto.PortfolioRequest;
import portfolio.dto.PortfolioResponse;
import portfolio.entity.Portfolio;
import portfolio.entity.User;
import portfolio.exception.NotFoundException;
import portfolio.mapper.PortfolioMapper;
import portfolio.repository.PortfolioRepository;
import portfolio.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private PortfolioMapper portfolioMapper;

    @InjectMocks
    private PortfolioService portfolioService;

    public PortfolioServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePortfolioForUser_Success() {
        String username = "testUser";
        User user = new User();
        user.setId(1L);
        user.setUsername(username);

        PortfolioRequest request = new PortfolioRequest();
        request.setProjects("Test Projects");
        request.setUsername(username);
        request.setSkills("Java, Spring Boot");
        request.setExperience("5 years");
        request.setEducation("Bachelor's");

        Portfolio portfolio = new Portfolio();
        PortfolioResponse response = new PortfolioResponse();
        response.setProjects("Test Projects");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(portfolioRepository.existsByUser(Optional.ofNullable(any(User.class)))).thenReturn(false); // User exists but no portfolio
        when(portfolioMapper.toPortfolio(request)).thenReturn(portfolio);
        when(portfolioMapper.toPortfolioResponse(portfolio)).thenReturn(response);

        PortfolioResponse result = portfolioService.createPortfolioForUser(username, request);

        assertNotNull(result);
        assertEquals("Test Projects", result.getProjects());

        verify(userRepository, times(1)).findByUsername(username);
        verify(portfolioRepository, times(1)).existsByUser(Optional.ofNullable(any(User.class)));
        verify(portfolioMapper, times(1)).toPortfolio(request);
        verify(portfolioRepository, times(1)).save(portfolio);
        verify(portfolioMapper, times(1)).toPortfolioResponse(portfolio);
    }

    @Test
    void testCreatePortfolioForUser_UserNotFound() {
        String username = "nonExistentUser";
        PortfolioRequest request = new PortfolioRequest();

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            portfolioService.createPortfolioForUser(username, request);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findByUsername(username);
        verify(portfolioRepository, never()).existsByUser(any());
    }

    @Test
    void testGetPortfolioByUsername_Success() {
        String username = "testUser";
        Portfolio portfolio = new Portfolio();
        portfolio.setProjects("Test Projects");
        PortfolioResponse response = new PortfolioResponse();
        response.setProjects("Test Projects");

        when(portfolioRepository.findByUsername(username)).thenReturn(Optional.of(portfolio));
        when(portfolioMapper.toPortfolioResponse(portfolio)).thenReturn(response);

        PortfolioResponse result = portfolioService.getPortfolioByUsername(username);

        assertNotNull(result);
        assertEquals("Test Projects", result.getProjects());

        verify(portfolioRepository, times(1)).findByUsername(username);
        verify(portfolioMapper, times(1)).toPortfolioResponse(portfolio);
    }

    @Test
    void testGetPortfolioByUsername_NotFound() {
        String username = "nonExistentUser";

        when(portfolioRepository.findByUsername(username)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            portfolioService.getPortfolioByUsername(username);
        });

        assertEquals("Portfolio not found for username: " + username, exception.getMessage());
        verify(portfolioRepository, times(1)).findByUsername(username);
        verify(portfolioMapper, never()).toPortfolioResponse(any());
    }

    @Test
    void testDeletePortfolioById() {
        Long portfolioId = 1L;

        portfolioService.deletePortfolioById(portfolioId);

        verify(portfolioRepository, times(1)).deleteById(portfolioId);
    }
}

