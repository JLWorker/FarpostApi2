package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.configs.SpringSecurityConfig;
import org.farpost.farpostapi2.dto.admin_dto.AdminDto;
import org.farpost.farpostapi2.enitities.User;
import org.farpost.farpostapi2.exceptions.users.InvalidUserCredentials;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.repositories.UserRepository;
import org.farpost.farpostapi2.services.JwtService;
import org.farpost.farpostapi2.services.utils.JwtClaim;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final SpringSecurityConfig springSecurityConfig;

    @Transactional(readOnly = true)
    public String getAccessToken(AdminDto adminDto){
        User user = userRepository.findUserByUsername(adminDto.getUsername());
        if (user == null || !springSecurityConfig.bCryptPasswordEncoder().matches(adminDto.getPassword(), user.getPassword())){
            throw new InvalidUserCredentials("User credentials invalid or user is null");
        }
        else {
            Map<String, Object> claims = Map.of(
                    JwtClaim.USERNAME.getName(), adminDto.getUsername(),
                    JwtClaim.ROLE.getName(), user.getRole()
            );
            return jwtService.swaggerJwtCreator(claims);
        }
    }

}
