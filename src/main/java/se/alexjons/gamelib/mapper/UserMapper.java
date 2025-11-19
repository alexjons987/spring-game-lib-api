package se.alexjons.gamelib.mapper;

import org.springframework.stereotype.Component;
import se.alexjons.gamelib.dto.AppUserRequestDTO;
import se.alexjons.gamelib.dto.AppUserResponseDTO;
import se.alexjons.gamelib.entity.AppUser;

@Component
public class UserMapper {

    public AppUserResponseDTO toDTO(AppUser appUser) {
        if (appUser == null) return null;

        return new AppUserResponseDTO(
                appUser.getUsername()
        );
    }

    public AppUser toEntity(AppUserRequestDTO appUserRequestDTO) {
        if (appUserRequestDTO == null) return null;

        return new AppUser(
                appUserRequestDTO.getUsername(),
                appUserRequestDTO.getPassword()
        );
    }
}
