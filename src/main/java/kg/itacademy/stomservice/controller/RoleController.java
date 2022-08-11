package kg.itacademy.stomservice.controller;

import kg.itacademy.stomservice.entity.Role;
import kg.itacademy.stomservice.models.RoleModel;
import kg.itacademy.stomservice.repositories.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {
    final RoleRepository roleRepository;

    @PostMapping("/create")
    public String createRole(@RequestBody RoleModel roleModel) {
        Role role = new Role();
        role.setNameRole(roleModel.getName());
        return roleRepository.save(role).getNameRole();
    }

}