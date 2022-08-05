package kg.itacademy.stomservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "stom_service_roles")
@Getter
@Setter
public class Role extends BaseEntity {
    @Column(name = "name_role", nullable = false, unique = true)
    private String nameRole;

}