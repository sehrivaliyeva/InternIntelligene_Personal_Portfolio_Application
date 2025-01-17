package portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import portfolio.dto.PortfolioRequest;
import portfolio.dto.PortfolioResponse;
import portfolio.dto.PortfolioUpdateRequest;
import portfolio.exception.NotFoundException;
import portfolio.service.PortfolioService;

@RestController
@RequestMapping("/v1/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/create")
    public ResponseEntity<PortfolioResponse> createPortfolio(@RequestParam String username,
                                                             @RequestBody PortfolioRequest portfolioRequest) {
        PortfolioResponse portfolioResponse = portfolioService.createPortfolioForUser(username, portfolioRequest);
        return ResponseEntity.ok(portfolioResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PortfolioResponse> updatePortfolio(
            @PathVariable Long id,
            @RequestBody PortfolioUpdateRequest portfolioRequest) {
        PortfolioResponse updatedPortfolio = portfolioService.updatePortfolio(id, portfolioRequest);
        return ResponseEntity.ok(updatedPortfolio);
    }

    @GetMapping("/get-portfolio")
    public ResponseEntity<PortfolioResponse> getPortfolioByUsername(@RequestParam String username) {
        try {
            PortfolioResponse portfolioResponse = portfolioService.getPortfolioByUsername(username);
            return ResponseEntity.ok(portfolioResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }


    @DeleteMapping("/delete-portfolio")
    public ResponseEntity<?> deletePortfolio(@RequestParam Long id) {
        try {
            portfolioService.deletePortfolioById(id);
            return ResponseEntity.ok("Portfolio deleted successfully.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Portfolio not found for id: " + id);
        }
    }

}
