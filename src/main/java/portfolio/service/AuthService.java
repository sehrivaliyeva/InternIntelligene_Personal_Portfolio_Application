package portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import portfolio.dto.LoginDto;
import portfolio.dto.UserDto;
import portfolio.entity.User;
import portfolio.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;


    public String register(UserDto userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already exists");


        }

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(LoginDto loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Load UserDetails from MyUserDetailsService
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());

        // Generate JWT Token using userDetails.getUsername()
        return jwtService.generateToken(userDetails.getUsername());
    }
}

