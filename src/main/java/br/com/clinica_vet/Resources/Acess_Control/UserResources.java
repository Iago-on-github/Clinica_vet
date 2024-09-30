package br.com.clinica_vet.Resources.Acess_Control;

import br.com.clinica_vet.Domain.Dto.UserDto;
import br.com.clinica_vet.Domain.User;
import br.com.clinica_vet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserResources {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto, UriComponentsBuilder componentsBuilder) {
        User user = userService.create(userDto);
        UserDto dto = new UserDto(user.getId(), user.getName(), user.getPassword());
        URI uri = componentsBuilder.path("/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
