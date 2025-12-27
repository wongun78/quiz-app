package fpt.kiennt169.springboot.dtos.roles;

import fpt.kiennt169.springboot.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Role information response")
public record RoleResponseDTO(
    
    @Schema(description = "Role ID", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID id,
    
    @Schema(description = "Role name", example = "ROLE_ADMIN", allowableValues = {"ROLE_ADMIN", "ROLE_USER"})
    RoleEnum name
) {}
