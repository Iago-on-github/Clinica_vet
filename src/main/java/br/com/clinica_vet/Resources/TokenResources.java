package br.com.clinica_vet.Resources;

import br.com.clinica_vet.Domain.Dto.UserRequest;
import br.com.clinica_vet.Domain.Dto.UserResponse;
import br.com.clinica_vet.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/login")
public class TokenResources {
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public TokenResources(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    public ResponseEntity<UserResponse> login (@RequestBody UserRequest userRequest){
        var user = userRepository.findByLogin(userRequest.login());
        if (user.isEmpty() || !user.get().isLoginCorrect(userRequest, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("user or password is invalid");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var clamis = JwtClaimsSet.builder().
                issuer("Vet Clinic Backend Team")
                .subject(user.get().getId())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(clamis)).getTokenValue();

        return ResponseEntity.ok(new UserResponse(jwtValue, expiresIn));
    }
}
