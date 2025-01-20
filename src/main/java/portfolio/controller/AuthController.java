package portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.dto.LoginDto;
import portfolio.dto.UserDto;
import portfolio.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/user-register")
    public ResponseEntity<?> register(@RequestBody UserDto userDTO) {
        return ResponseEntity.ok(authService.register(userDTO));
    }

    @PostMapping("/user-login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDTO) {
        String token = authService.login(loginDTO);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
