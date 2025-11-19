package se.alexjons.gamelib.service;

import org.springframework.stereotype.Service;
import se.alexjons.gamelib.dto.AppUserResponseDTO;
import se.alexjons.gamelib.entity.AppUser;
import se.alexjons.gamelib.exception.ResourceNotFoundException;
import se.alexjons.gamelib.mapper.UserMapper;
import se.alexjons.gamelib.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<AppUserResponseDTO> getAllUsers() {
        List<AppUser> games = userRepository.findAll();

        if (!games.isEmpty()) {
            return games.stream()
                    .map(userMapper::toDTO)
                    .toList();
        } else {
            throw new ResourceNotFoundException("No users were found");
        }
    }
}
