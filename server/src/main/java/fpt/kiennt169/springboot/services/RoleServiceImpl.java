package fpt.kiennt169.springboot.services;

import fpt.kiennt169.springboot.dtos.PageResponseDTO;
import fpt.kiennt169.springboot.dtos.roles.RoleRequestDTO;
import fpt.kiennt169.springboot.dtos.roles.RoleResponseDTO;
import fpt.kiennt169.springboot.entities.Role;
import fpt.kiennt169.springboot.exceptions.ResourceNotFoundException;
import fpt.kiennt169.springboot.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public RoleResponseDTO create(RoleRequestDTO requestDTO) {
        Role role = new Role();
        role.setName(requestDTO.name());
        Role savedRole = roleRepository.save(role);
        return new RoleResponseDTO(savedRole.getId(), savedRole.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<RoleResponseDTO> getAll(Pageable pageable) {
        Page<Role> rolePage = roleRepository.findAll(pageable);
        Page<RoleResponseDTO> responsePage = rolePage.map(role -> 
            new RoleResponseDTO(role.getId(), role.getName())
        );
        return PageResponseDTO.from(responsePage);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponseDTO getById(UUID id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        return new RoleResponseDTO(role.getId(), role.getName());
    }

    @Override
    @Transactional
    public RoleResponseDTO update(UUID id, RoleRequestDTO requestDTO) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        role.setName(requestDTO.name());
        Role updatedRole = roleRepository.save(role);
        return new RoleResponseDTO(updatedRole.getId(), updatedRole.getName());
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role", "id", id);
        }
        roleRepository.deleteById(id);
    }
}
