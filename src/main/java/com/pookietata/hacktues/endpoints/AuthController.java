package com.pookietata.hacktues.endpoints;

import com.pookietata.hacktues.Dtos.RiotAccountDto;
import com.pookietata.hacktues.models.Role;
import com.pookietata.hacktues.models.User;
import com.pookietata.hacktues.models.enums.RoleName;
import com.pookietata.hacktues.payloads.ApiResponse;

import java.net.URI;
import java.util.Collections;
import com.pookietata.hacktues.payloads.JwtAuthenticationResponse;
import com.pookietata.hacktues.payloads.LoginRequest;
import com.pookietata.hacktues.payloads.SignUpRequest;
import com.pookietata.hacktues.repositories.RoleRepository;
import com.pookietata.hacktues.repositories.UserRepository;
import com.pookietata.hacktues.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${riot.api.key}")
    private String riotApiKey;

    @Value("${riot.api.url}")
    private String riotApiUrl;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmailOrSummonerName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerEntity(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Email Address already in use!"));
        }

        if (userRepository.existsBySummonerName(signUpRequest.getSummonerName())) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Summoner's name already in use!"));
        }

        User user = createUser(signUpRequest);

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    private User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setSummonerName(signUpRequest.getSummonerName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setCurrencyBalance(100); // Default currency balance
        user.setSummonerTag(signUpRequest.getSummonerTag());
        String puuid = getPUUIDFromRiotAPI(signUpRequest.getSummonerName(), signUpRequest.getSummonerTag());
        user.setPUUID(puuid);
        // Convert the string to an enum
        RoleName roleNameEnum = RoleName.valueOf("ROLE_USER");

        Role userRole = roleRepository.findByName(roleNameEnum)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRoles(Collections.singleton(userRole));
        return user;
    }
    private String getPUUIDFromRiotAPI(String gameName, String tagLine) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", riotApiKey); // Use the injected API key
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String urlTemplate = riotApiUrl + "/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}"; // Use the injected API URL
        String url = urlTemplate.replace("{gameName}", gameName).replace("{tagLine}", tagLine);
        System.out.println(url);

        ResponseEntity<RiotAccountDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, RiotAccountDto.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getPuuid();
        } else {
            // handle error scenario, perhaps throw a custom exception
            throw new RuntimeException("Failed to retrieve PUUID from Riot API");
        }
    }

}
