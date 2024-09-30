package br.com.clinica_vet.Resources.SecurityConfigurations;

import br.com.clinica_vet.Domain.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationResources {
    @Autowired
    private AuthenticationManager manager;
    @PostMapping
    public ResponseEntity<UserDto> authenticate(UserDto dto){
        var token = new UsernamePasswordAuthenticationToken(dto.name(), dto.password());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
