package fpt.kiennt169.springboot.mappers;

import fpt.kiennt169.springboot.dtos.users.UserRequestDTO;
import fpt.kiennt169.springboot.dtos.users.UserResponseDTO;
import fpt.kiennt169.springboot.entities.User;
import fpt.kiennt169.springboot.enums.RoleEnum;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T12:24:40+0700",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.9 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UUID id = null;
        String email = null;
        String fullName = null;
        Boolean active = null;

        id = user.getId();
        email = user.getEmail();
        fullName = user.getFullName();
        active = user.getActive();

        Set<RoleEnum> roles = mapRolesToEnums(user.getRoles());

        UserResponseDTO userResponseDTO = new UserResponseDTO( id, email, fullName, active, roles );

        return userResponseDTO;
    }

    @Override
    public User toEntity(UserRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( requestDTO.email() );
        user.setFullName( requestDTO.fullName() );
        user.setActive( requestDTO.active() );

        return user;
    }

    @Override
    public void updateEntityFromDTO(UserRequestDTO requestDTO, User user) {
        if ( requestDTO == null ) {
            return;
        }

        if ( requestDTO.email() != null ) {
            user.setEmail( requestDTO.email() );
        }
        if ( requestDTO.fullName() != null ) {
            user.setFullName( requestDTO.fullName() );
        }
        if ( requestDTO.active() != null ) {
            user.setActive( requestDTO.active() );
        }
    }
}
