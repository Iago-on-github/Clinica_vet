package br.com.clinica_vet.Service;

import br.com.clinica_vet.Domain.Dto.UserDto;
import br.com.clinica_vet.Domain.User;
import br.com.clinica_vet.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(UserDto userDto) {
        return userRepository.save(helperCreate(userDto));
    }

    private User helperCreate(UserDto dto) {
        User user = new User();
        user.setName(dto.name());
        user.setPassword(dto.password());
        return user;
    }
}
