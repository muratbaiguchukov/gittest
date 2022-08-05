package kg.itacademy.stomservice.repository;

import kg.itacademy.stomservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findFirstByNameRole(String nameRole);
}