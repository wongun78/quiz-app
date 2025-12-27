package fpt.kiennt169.springboot.dtos.roles;

import fpt.kiennt169.springboot.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Role request for create/update")
public record RoleRequestDTO(
    
    @Schema(description = "Role name", example = "ROLE_ADMIN", allowableValues = {"ROLE_ADMIN", "ROLE_USER"})
    @NotNull(message = "Role name is required")
    RoleEnum name
) {}
